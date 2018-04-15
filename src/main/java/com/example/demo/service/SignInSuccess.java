package com.example.demo.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserRepository;

/**
*
* [설명] 	로그인 성공 시 throw를 받아 db의 최근 로그인(lastLogin) 데이터 값을 변동하는 기능을 수행한다.
*		Transactional annotation은 트랜잭션 처리와 선언적 트랜잭션을 지원한다.
*		이를 이용해 쿼리문이 정상적으로 완료되거나, 처리 도중 에러가 났을 때 자동으로 rollback 해줄 수 있다.
*
*		선언적 트랜잭션 처리
*		- 설정 파일이나 어노테이션을 이용해서 트랜잭션의 범위, 롤백 규칙 등을 정의
*
* @file : SignInSuccess.java
* @package : com.example.demo.Service
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 14.
*/
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SignInSuccess implements ApplicationListener<AuthenticationSuccessEvent> {
	
	@Autowired
	UserRepository userRepository;
	
	// System.out.println() 함수를 대신해서 사용.
	Logger logger = LoggerFactory.getLogger(SignInSuccess.class);
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		
		// 사용자 ID 가져오기
		String name = event.getAuthentication().getName();
		
		LocalDate localDate = LocalDate.now();
		
		logger.info("계정: " + name);
		logger.info("날짜: " + localDate);
		
		userRepository.update(name, localDate);
		
	}
}
