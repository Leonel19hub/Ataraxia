package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Publicacion;
import com.ataraxia.model.Usuario;
import com.ataraxia.service.IPublicacionService;
import com.ataraxia.service.IUsuarioService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class ApplicationController {

	@Autowired
	IUsuarioService usuarioService;

	@Autowired
	IPublicacionService postService;

	@GetMapping("/")
	public ModelAndView appHome(Authentication authentication) throws Exception {
		ModelAndView modelView = new ModelAndView("index");
		Usuario userDetalis = new Usuario();
		if (usuarioService.showUsers().size() == 0) {
			Usuario firstAdmin = new Usuario();
			firstAdmin.setIdUser(1);
			firstAdmin.setName("Lionel");
			firstAdmin.setLastname("Messi");
			firstAdmin.setUserName("THE GOAT");
			firstAdmin.setEmail("admin@gmail.com");
			firstAdmin.setPassword("12345");
			usuarioService.saveUserAdmin(firstAdmin);
			return modelView;
		}
		else{
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
		}
		
		return modelView;	
	}
	
	@GetMapping("/Contenido")
	public ModelAndView content(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("contenido");
		if(!authentication.isAuthenticated()){
			modelView.addObject("userLogin", false);
		}
		else{
			modelView.addObject("userLogin", true);
			// Get publicar un post
			Publicacion postFromContent = new Publicacion();
			modelView.addObject("postContent", postFromContent);
			// Usario ADMIN online
			Usuario userOnline = new Usuario();
			userOnline = usuarioService.searchUser(authentication.getName());
			modelView.addObject("userDetails", userOnline);
			// Listar las publicaciones
			// modelView.addObject("listPost", postService.showAllPost());
			// Estas dos lineas dan vuelta la lista, para que la ultima sea la primera a la vista
			List<Publicacion> listReverse = postService.showAllPost();
			Collections.reverse(listReverse);
			modelView.addObject("listPost", listReverse);

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
