package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*
* [설명] boot web system
* 		프로젝트의 로그인 서비스 개발 참고 사이트
* 		: https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
* 		controller, service, dao, entity 참고 사이트 (Jpa)
* 		: http://yellowh.tistory.com/109
*
* @file : LockerApplication.java
* @package : com.example.demo
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 3. 13.
*/
@SpringBootApplication
public class LockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockerApplication.class, args);
	}
}
