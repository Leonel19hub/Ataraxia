package com.ataraxia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Usuario;
import com.ataraxia.service.IUsuarioService;

@Controller
public class UsuarioController {

    @Autowired
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
    public String saveUser(@Valid @ModelAttribute("unUsuario")Usuario usuarioNuevo, BindingResult resultado, ModelMap model){
        if (resultado.hasErrors()) {
			model.addAttribute("unUsuario", usuarioNuevo);
			model.addAttribute("band", false);
			return "newUser";
		}
		try {
			usuarioService.saveUser(usuarioNuevo);
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unUsuario", usuarioNuevo);
			model.addAttribute("band", false);
			return "newUser";
		}

		model.addAttribute("formUsuarioErrorMessage", "Usuario guardado correctamente");
		model.addAttribute("unUsuario", nuevoUsuario);
		model.addAttribute("band", false);
		return "newUser";
    }
}
