package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

/**
*
* [설명] 	Database의 Entity 정보를 위한 class로
* 		사용자의 정보를 받는다.
*
* @file : AppUser.java
* @package : com.example.demo.entity
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
*/
@Entity
@Table( name = "App_User", schema = "lockeruser", //
        uniqueConstraints = { //
        @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
@Getter @Setter
public class AppUser {
 
    @Id
    @GeneratedValue
    @Column(name = "User_No", nullable = false)
    private Long userNo;
 
    @Column(name = "User_ID", length = 32, nullable = false)
    private String userId;
 
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;
    
    @Column(name = "User_Email", length = 64, nullable = false)
    private String userEmail;
    
    @Column(name = "User_Name", length = 32, nullable = false)
    private String userName;
    
    // CreatedDate는 entity 생성시 자동으로 최근 날짜를 반영하는 annotation
    @CreatedDate
    @Column(name = "Created_Date", updatable = false)
    private LocalDate createdDate;
    
    // LastModifiedDate는 entity 수정시 자동으로 최근 날짜를 반영하는 annotation
    @LastModifiedDate
    @Column(name = "Modified_Date", updatable = true)
    private LocalDate ModifiedDate;
    
    @Column(name = "last_Login", updatable = true)
    private LocalDate lastLogin;
 
    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;
 
}