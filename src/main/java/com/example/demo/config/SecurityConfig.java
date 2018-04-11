package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserDetailsServiceImpl;

/**
*
* [설명] 웹 서비스 이용자에 따른 접근제한 설정
*
* @file : SecurityConfig.java
* @package : com.example.demo.config
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 3. 27.
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	// 스프링 시큐리티 5 이상 부터는 password 앞에 식별자가 필요로 한다.
	// 암호화를 사용하지 않을 경우 {noop}을 붙여줘야 정상 작동한다.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			// 자체적으로 계정 생성 방법
			// .inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
			// service에 접근하여 계정 데이터 조회 / 비밀번호는 Encrypt로 encoding후 db에 접근한다.
			.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	//permitAll을 통한 사이트 전체 잠금 해제 (?)
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		//인증을 풀어줄 요소 (주로 리소스)
		web.ignoring().antMatchers("/css/**", "/script/**", "/image/**", "/fonts/**", "/lib/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//리소스외에 페이지의 인증/비인증/인증권한 등을 설정
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //admin 이하의 내용은 Admin 권한만 접근 가능. antMatchers().hasRole(ROLE_ADMIN")은 안됨
			.antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // 해당 권한을 가진 자만 접근 가능
			.antMatchers("/**").authenticated() //승인된 이용자만 접근 가능
			//.antMatchers("/**").permitAll() //누구든 접근 가능
			;
		
		// 권한을 소유하지 않아서 접근 실패 시 AccessDeniedException이 처리해 보여줄 페이지
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests().and()
			.formLogin()
			//로그인 처리 페이지
			.loginProcessingUrl("/j_spring_security_check") // 뷰에서 요청한 url
			//로그인 페이지
			.loginPage("/login").permitAll()
			//로그인 실패시 보여줄 에러페이지
			.failureUrl("/login?error=true") // login.html에서 이 url을 받을 경우 에러 원인을 나타내준다.
			.usernameParameter("username1") // view에서 입력하는 name 설정
            .passwordParameter("password")
			//로그아웃
			.and().logout()
			// /logout을 호출할 경우 로그아웃
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			// 로그아웃 후 이동할 url
			.logoutSuccessUrl("/")
			;
		
		// Config Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	}
	
	//Remember Me option으로, database에 접근하여 저장한다.
	//Token stored in Table (Persistent_Logins)
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
