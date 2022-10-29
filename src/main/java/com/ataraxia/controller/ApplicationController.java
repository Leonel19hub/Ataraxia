package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

	@GetMapping("/")
	public String appHome() {
		return "index";	
	}

	@GetMapping("/login")
	public String ingresar() {
		return "login";
	}
}
