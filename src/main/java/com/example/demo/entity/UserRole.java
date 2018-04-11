package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
*
* [설명] 	Database의 Entity 정보를 받기 위한 class로
* 		사용자의 권한 정보를 받는다.
*
* @file : UserRole.java
* @package : com.example.demo.entity
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
*/
@Entity
@Table( name = "User_Role", schema = "lockeruser", //
        uniqueConstraints = { //
        @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_No", "Role_Id" }) })
@Getter @Setter
public class UserRole {
 
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_No", nullable = false)
    private AppUser appUser;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_Id", nullable = false)
    private AppRole appRole;
 
}