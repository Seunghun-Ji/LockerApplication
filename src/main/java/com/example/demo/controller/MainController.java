package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AppUser;
import com.example.demo.service.RepositoryServiceImpl;

/**
*
* [설명] Controller setting
*
* @file : MainController.java
* @package : com.example.demo.controller
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 3. 13.
*/
@Controller
public class MainController {

	@Autowired
	RepositoryServiceImpl repositoryService;
	
	@RequestMapping(value="/")
	public String mainPage() {
		return "index";
	}
	
	@RequestMapping(value="/monitor")
	public String testMonitorPage() {
		return "monitor";
	}
	
	@RequestMapping(value="/index")
	public String indexPage() {
		return "index2";
	}
	
	@RequestMapping(value="/admin")
	public String adminPage() {
		return "/admin/test";
	}
	
	@RequestMapping(value="/login")
	public String loginPage() {
		return "/login";
	}

	@RequestMapping("/create")
	public String create() {
		return "create";
	}
	
	@RequestMapping(value="/createProcessing", method=RequestMethod.POST)
	public String createProcessing(AppUser appUser, @RequestParam("password") String password) {
		
		// 계정 생성 (구체적인 구현은 RepositoryServiceImpl 부분 참고
		repositoryService.create(appUser, password);
		
		// Redirect:/login로 입력하면 안된다. 꼭 redirect:/login 으로 써야한다.
		return "redirect:/login"; //회원가입 성공 시 login 페이지로 넘어간다.
	}
	
}
