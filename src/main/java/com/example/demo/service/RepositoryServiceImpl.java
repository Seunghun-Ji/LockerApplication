package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.AppRole;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.UserRole;

/**
*
* [설명] 	사용자 등록을 위한 서비스.
* 		비밀번호는 db에 저장 시 암호화하여 저장한다.
*
* @file : RepositoryServiceImpl.java
* @package : com.example.demo.Service
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 14.
*/
@Service
public class RepositoryServiceImpl {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Transactional
	public void create(AppUser appUser, String password) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		LocalDate localDate = LocalDate.now();
		
		// 사용자 정보 추가 입력
		appUser.setEncrytedPassword(encoder.encode(password));
		appUser.setCreatedDate(localDate);
		appUser.setModifiedDate(localDate);
		appUser.setEnabled(true);
		
		// 권한 정보 생성
		AppRole appRole = new AppRole();
		appRole.setRoleId(2L);
		appRole.setRoleName("ROLE_USER");
		
		// 사용자 권한 등록
		UserRole userRole = new UserRole();
		userRole.setAppUser(appUser);
		userRole.setAppRole(appRole);
		
		// DB에 저장
		userRepository.save(appUser);
		roleRepository.save(userRole);

	}
	
}
