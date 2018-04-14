package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*
* [설명] 	2018년 1학기 종합설계 프로젝트로, IoT 장치와 Block chain과의 연동을 통해 웹 상에서 Door IoT 장치 관련
* 		서비스 계약 및 개폐 제어를 할 수 있고, Block chain을 통해 분산 원장으로 서비스 이용 내역을 기록하는 것이 목적이다.
* 		모든 파일 주석에 아래의 link 부분이 있는 경우, 원본을 참고하거나 아예 그대로 가져와서 공부하며 진행했다.
* 
* 		프로젝트의 로그인 서비스 개발 참고 사이트
* 		: http://yellowh.tistory.com/109
*
* @file : LockerApplication.java
* @package : com.example.demo
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 3. 13.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
* 		  
*/
@SpringBootApplication
public class LockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockerApplication.class, args);
	}
}
