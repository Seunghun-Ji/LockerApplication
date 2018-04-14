package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
 
import com.example.demo.dao.AppUserDAO;
import com.example.demo.entity.AppUser;
import com.example.demo.dao.AppRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
*
* [설명] 	DAO 객체를 통해 DB의 데이터를 접근하여 Controller로 넘겨주는 역할
* 		로그인 시, 사용자가 입력한 정보를 토대로 결과를 console 창에 띄워주도록 코드가 구현되어있다.
* 		53 line부터는 어떤 기능을 하는지 모르므로 추가 공부 필요. 
* 
* 		참고 사이트: https://code.i-harness.com/ko/q/129ef04
*
* @file : UserDetailsServiceImpl.java
* @package : com.example.demo.service
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AppUserDAO appUserDAO;
 
    @Autowired
    private AppRoleDAO appRoleDAO;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserDAO.findUserAccount(userName);
 
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserNo());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,.. 을 기반으로 권한 리스트를 만들어준다.
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        
        // 사용자의 ID, 비밀번호, 접근 권한, 권한 리스트를 집어넣는다.
        // (ID, 비밀번호, 권한 리스트) 생성자는 있지만
        // (ID, 비밀번호, 사용가능여부, 권한 리스트) 생성자는 없어서 다음과 같은 생성자로 설정했다.
        UserDetails userDetails = (UserDetails) new User(appUser.getUserId(), //
                appUser.getEncrytedPassword(), appUser.isEnabled(), true, true, true, grantList);
        
        return userDetails;
    }
 
}