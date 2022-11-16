package com.ataraxia.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Usuario;
import com.ataraxia.service.IUsuarioService;

@Controller
public class UsuarioController {

	private static final Log ATARAXIA = LogFactory.getLog(UsuarioController.class);

	@Autowired
	// @Qualifier("nuevoUsuario")
    Usuario nuevoUsuario;

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/registroUsuario")
    ModelAndView uploadUser(){
        ModelAndView modelView = new ModelAndView("newUser");
        modelView.addObject("unUsuario", nuevoUsuario);
        return modelView;
    }

    @PostMapping("/guardarUsuario")
    public ModelAndView saveUser(@Valid @ModelAttribute("unUsuario")Usuario usuarioNuevo, BindingResult resultado, ModelMap model){
		ATARAXIA.info("* Inresando al metodo: saveUser *");

		ModelAndView  modelView = new ModelAndView();
        if (resultado.hasErrors()) {
			modelView.setViewName("newUser");
			modelView.addObject("unUsuario", usuarioNuevo);
			// model.addAttribute("unUsuario", usuarioNuevo);
			// model.addAttribute("band", false);
			return modelView;
		}
		try {
			// byte[] content = file.getBytes();
			// String base64 = Base64.getEncoder().encodeToString(content);
			// usuarioNuevo.setAvatar(base64);
			ATARAXIA.info("* Guardando Usuario *");
			usuarioService.saveUser(usuarioNuevo);
		} catch (Exception e) {
			modelView.addObject("formUsuarioErrorMessage", e.getMessage());
			modelView.addObject("unUsuario", usuarioService.newUser());
			ATARAXIA.info("* Saliendo del metodo PostMapping: saveUser *");
			modelView.setViewName("newUser");
			return modelView;
		}

		modelView.addObject("fomrUsuarioErrorMesage", "Usuario Guardado correctamente");
		modelView.addObject("unUsuario", usuarioService.newUser());
		modelView.setViewName("newUser");
		return modelView;
    }

	Usuario trash;

	@GetMapping("/Mi-Perfil/{nameOfUser}")
	public ModelAndView viewProfile(Authentication authentication, @PathVariable(name = "nameOfUser") String nameOfUser) throws Exception{
		ModelAndView modelView = new ModelAndView("miPerfil");
		Usuario findUser = new Usuario();
		if (authentication == null) {
			ATARAXIA.info("No inicio sesion");
		}
		else{
			try {
				findUser = usuarioService.searchUser(authentication.getName());
				ATARAXIA.info("--------------------"+authentication.getName());
			} catch (Exception e) {
				ATARAXIA.fatal("Error: "+e.getMessage());
			}
			ATARAXIA.info("Ingresando al perfil del usuario: "+nameOfUser);
			modelView.addObject("userLogin", true);
			Usuario userDetalis = new Usuario();
			userDetalis = usuarioService.searchUser(authentication.getName());
			modelView.addObject("userD", userDetalis);
			modelView.addObject("aUser", findUser);
			trash = findUser;
		}
		return modelView;
	}

	@PostMapping("/Mi-Perfil")
	public ModelAndView postEditUser(@ModelAttribute("userF") Usuario userMod){
		ModelAndView modelView = new ModelAndView();
		// userMod = trash;
		ATARAXIA.info("VERIFICACION: "+userMod.getIdUser());
		usuarioService.editUser(userMod);
		modelView.addObject("userD", userMod);
		modelView.addObject("aUser", userMod);
		modelView.setViewName("miPerfil");

		return modelView;
	}
}
