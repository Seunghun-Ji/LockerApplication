package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.AppUser;

/**
*
* [설명] JPA Controller setting 연습용 파일.
* 		RestController annotation는 Controller와 ResponseBody annotation을 하나로 합친 기능이다.
* 		ResponseBody annotation 기능은 return 값에 view를 반환하지 않고 http response body에 직접 쓰여지게 하는 것이다.
*
* @file : UserResourceController.java
* @package : com.example.demo.controller
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
*/
@RestController
public class UserResourceController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/user_list")
	public List<AppUser> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
}
