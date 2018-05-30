package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.communications.RaspPiClient;
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
		return "main/index";
	}
	
	@RequestMapping(value="/aboutUs")
	public String aboutUsPage() {
		return "main/aboutUs";
	}
	
	@RequestMapping(value="/features")
	public String featuresPage() {
		return "main/features";
	}

	@RequestMapping("/signUp")
	public String create() {
		return "portal/create";
	}

	@RequestMapping(value="/info")
	public String infoPage(Principal principal, Model model) {
		model.addAttribute("userId", principal.getName());
		return "enroll/info";
	}
	
	// Thymeleaf로 정보를 넘기는 방법
	// link: http://chomman.github.io/blog/spring%20framework/spring-security%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%82%AC%EC%9A%A9%EC%9E%90%EC%9D%98-%EC%A0%95%EB%B3%B4%EB%A5%BC-%EC%B0%BE%EB%8A%94-%EB%B0%A9%EB%B2%95/
	@CrossOrigin("*")
	@RequestMapping(value="/index")
	public String indexPage(Principal principal, Model model) {
		// 사용자 ID 정보는 principal.getName()으로 가져올 수 있다.
		model.addAttribute("userId", principal.getName());
		return "index3";
	}
	
	@RequestMapping(value="/admin")
	public String adminPage() {
		return "/admin/test";
	}
	
	@RequestMapping(value="/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "/test";
	}
	
	@RequestMapping(value="/data", method=RequestMethod.POST)
	public String data(@RequestParam("led") String led, RedirectAttributes redirectAttributes) {
		System.out.println(led);
		RaspPiClient cm = new RaspPiClient();
		
		// test에게 수행 결과를 알려주기 위한 데이터 전달
		redirectAttributes.addAttribute("returnedData","success");
		
		cm.clientRun(led);
		
		return "redirect:/test";
	}
	
	@RequestMapping(value="/createProcessing", method=RequestMethod.POST)
	public String createProcessing(AppUser appUser, @RequestParam("password") String password) {
		
		// 계정 생성 (구체적인 구현은 RepositoryServiceImpl 부분 참고
		if(repositoryService.create(appUser, password) == "IdExsited") //id 중복
			return "redirect:/signUp?idexsited";
		// Redirect:/login로 입력하면 안된다. 꼭 redirect:/login 으로 써야한다.
		else
			return "redirect:/login"; //회원가입 성공 시 login 페이지로 넘어간다.
	}

}
