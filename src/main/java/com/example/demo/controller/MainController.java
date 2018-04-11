package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
* [설명] Controller setting
*
* @file : TestController.java
* @package : com.example.demo.controller
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 3. 13.
*/
@Controller
public class MainController {

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
}
