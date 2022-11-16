package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Usuario;
import com.ataraxia.service.IUsuarioService;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class ApplicationController {

	@Autowired
	IUsuarioService usuarioService;

	@GetMapping("/")
	public ModelAndView appHome(Authentication authentication) throws Exception {
		ModelAndView modelView = new ModelAndView("index");
		Usuario userDetalis = new Usuario();
		if (authentication == null) {
			modelView.addObject("userLogin", false);
			modelView.addObject("userD", null);
		}
		else{
			modelView.addObject("userLogin", true);
			userDetalis = usuarioService.searchUser(authentication.getName());
			modelView.addObject("userD", userDetalis);
			String nameLink = userDetalis.getName()+"-"+userDetalis.getLastname();
			modelView.addObject("nameOfUser", nameLink);
			System.out.println("---------"+nameLink+"----------");
		}
		return modelView;	
	}
	

	

	@GetMapping("/registro")
    public String tipoRegistro(){
        return "pre-registro";
    }

	@GetMapping("/login")
	public String ingresar() {
		return "login";
	}
}
