package com.example.demo.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AppUser;

/**
*
* [설명] 	쿼리문을 통해 DB에 접근하는 DAO. JpaRepository 인터페이스를 통해 사용한다.
* 		AppUserDAO.java와 별개로 만든 이유는 기반이 된 프로그램 방식을 그대로 유지하여 entityManager 방식도 공부하기 위해서 놔뒀다.
*
* @file : UserRepositiory.java
* @package : com.example.demo.dao
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
*/
public interface UserRepository extends JpaRepository <AppUser, Long> {
	
	@Query("SELECT u FROM AppUser u WHERE u.userId = :userId")
	public AppUser findOneById(@Param("userId") String userId); 
	
	@Modifying
	@Query("UPDATE AppUser u SET u.lastLogin = :lastLogin WHERE u.userId = :userId")
	public void update(@Param("userId") String userId, @Param("lastLogin") LocalDate lastLogin);
	
}
