package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
*
* [설명] 	encryted password가 어떤 값인지를 구하기 위한 샘플 코드
*
* @file : EncrytedPasswordUtils.java
* @package : com.example.demo.utils
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
*/
public class EncrytedPasswordUtils {
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        // password를 입력하면 encoding된 data 값을 알려준다.
    	String password = "123";
        String encrytedPassword = encrytePassword(password);
 
        System.out.println("Encryted Password: " + encrytedPassword);
    }
     
}