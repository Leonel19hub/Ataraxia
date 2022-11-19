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

	@GetMapping("/admin/dashboard")
	public ModelAndView adminDashboard(){
		ModelAndView modelView = new ModelAndView("admin/dashboard");
		return modelView;
	}

	@GetMapping("/admin/button")
	public ModelAndView buttonLink(){
		ModelAndView modelView = new ModelAndView("admin/button");
		return modelView;
	}

	@GetMapping("/admin/typography")
	public ModelAndView typographyLink(){
		ModelAndView modelView = new ModelAndView("admin/typography");
		return modelView;
	}

	@GetMapping("/admin/element")
	public ModelAndView elementLink(){
		ModelAndView modelView = new ModelAndView("admin/element");
		return modelView;
	}

	@GetMapping("/admin/widget")
	public ModelAndView widgetLink(){
		ModelAndView modelView = new ModelAndView("admin/widget");
		return modelView;
	}

	@GetMapping("/admin/form")
	public ModelAndView formLink(){
		ModelAndView modelView = new ModelAndView("admin/form");
		return modelView;
	}

	@GetMapping("/admin/table")
	public ModelAndView tableLink(){
		ModelAndView modelView = new ModelAndView("admin/table");
		return modelView;
	}

	@GetMapping("/admin/chart")
	public ModelAndView chartLink(){
		ModelAndView modelView = new ModelAndView("admin/chart");
		return modelView;
	}

	@GetMapping("/admin/signin")
	public ModelAndView siginLink(){
		ModelAndView modelView = new ModelAndView("admin/signin");
		return modelView;
	}

	@GetMapping("/admin/404")
	public ModelAndView errorLink(){
		ModelAndView modelView = new ModelAndView("admin/404");
		return modelView;
	}

	@GetMapping("/admin/signup")
	public ModelAndView singupLink(){
		ModelAndView modelView = new ModelAndView("admin/signup");
		return modelView;
	}

	@GetMapping("/admin/blank")
	public ModelAndView blankLink(){
		ModelAndView modelView = new ModelAndView("admin/blank");
		return modelView;
	}


	@GetMapping("/")
	public ModelAndView appHome(Authentication authentication) throws Exception {
		ModelAndView modelView = new ModelAndView("index");
		Usuario userDetalis = new Usuario();
		if (authentication == null) {
			modelView.addObject("userLogin", false);
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
	
	@GetMapping("/Contenido")
	public ModelAndView content(Authentication authentication){
		ModelAndView modelView = new ModelAndView("contenido");
		if(!authentication.isAuthenticated()){
			modelView.addObject("userLogin", false);
		}
		else{
			modelView.addObject("userLogin", true);
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
