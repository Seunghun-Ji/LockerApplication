package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {

	@RequestMapping(value="/signUp")
	public String signUpPage() {
		return "signUp";
	}
}
