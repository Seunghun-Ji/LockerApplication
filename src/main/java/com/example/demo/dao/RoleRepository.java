package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserRole;

/**
*
* [설명] 	쿼리문을 통해 DB에 접근하는 DAO. JpaRepository 인터페이스를 통해 사용한다.
* 		AppRoleDAO.java와 별개로 만든 이유는 기반이 된 프로그램 방식을 그대로 유지하여 entityManager 방식도 공부하기 위해서 놔뒀다.
*
* @file : RoleRepositiory.java
* @package : com.example.demo.dao
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
*/
public interface RoleRepository extends JpaRepository<UserRole, Long> {

}
