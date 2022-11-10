package com.ataraxia.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Usuario;
import com.ataraxia.service.IUsuarioService;

@Controller
public class UsuarioController {

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

    @PostMapping(value = "/guardarUsuario", consumes = "multipart/form-data")
    public ModelAndView saveUser(@Valid @ModelAttribute("unUsuario")Usuario usuarioNuevo, BindingResult resultado, ModelMap model, @RequestParam("file") MultipartFile file){
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
			usuarioService.saveUser(usuarioNuevo);
		} catch (Exception e) {
			modelView.addObject("formUsuarioErrorMessage", e.getMessage());
			modelView.addObject("unUsuario", usuarioNuevo);
			// model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			// model.addAttribute("unUsuario", usuarioNuevo);
			// model.addAttribute("band", false);
			modelView.setViewName("newUser");
		}

		// model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
		// model.addAttribute("unUsuario", nuevoUsuario);
		// model.addAttribute("band", false);
		modelView.addObject("fomrUsuarioErrorMesage", "Usuario Guardado");
		modelView.addObject("unUsuario", nuevoUsuario);
		modelView.setViewName("newUser");
		return modelView;
    }
}
