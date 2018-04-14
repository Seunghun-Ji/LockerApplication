package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
*
* [설명] 	Database의 Entity 정보를 위한 class로
* 		권한 정보를 받는다.
*
* @file : AppRole.java
* @package : com.example.demo.entity
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
*/
@Entity
@Table( name = "App_Role", schema = "lockeruser", //
        uniqueConstraints = { //
        @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
@Getter @Setter
public class AppRole {
     
    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;
 
    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
    
}