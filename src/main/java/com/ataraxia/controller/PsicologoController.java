package com.ataraxia.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Psicologo;
import com.ataraxia.model.Usuario;
import com.ataraxia.service.IPsicologoService;
import com.ataraxia.service.IUsuarioService;

@Controller
public class PsicologoController {

    private static final Log ATARAXIA = LogFactory.getLog(PsicologoController.class);
    
    @Autowired
    Psicologo nuevoPsico;

    @Autowired
    IPsicologoService psicoService;

    @Autowired
    IUsuarioService usuarioService;

    
    
    @GetMapping("/registroPsicologo")
    ModelAndView uploadUserPsico(){
        ATARAXIA.info("***** Ingresando al metodo: uploadUserPsico *****");
        ModelAndView modelView = new ModelAndView("newPsico");
        modelView.addObject("unPsico", nuevoPsico);
        return modelView;
    }

    // Despues agreagar codigo para añadir foto
    @PostMapping("/guardarPsicologo")
    public ModelAndView savePsico(@Valid @ModelAttribute ("unPsico") Psicologo psicoNuevo, BindingResult resultado, ModelMap model){

        ATARAXIA.info("***** Ingresando al metodo: savePsico *****");
        ModelAndView modelView = new ModelAndView();

        if (resultado.hasErrors()) {
            ATARAXIA.fatal("***** ERRROR DE VALIDACION *****");
            modelView.setViewName("newPsico");
            modelView.addObject("unPsico", psicoNuevo);
            return modelView;
        }

        try {
            ATARAXIA.info("***** Guardando Psicologo *****");
            psicoService.savePsicologo(psicoNuevo);
        } catch (Exception e) {
            modelView.addObject("psicoErrorMessage", e.getMessage());
            modelView.addObject("unPsico", psicoService.newPsico());
            ATARAXIA.fatal("***** Saliendo del metodo PostMapping: savePsico *****");
            modelView.setViewName("newPsico");
            return modelView;
        }

        modelView.addObject("piscoErrorMessage", "Psicologo guardado correctamente");
        modelView.addObject("unPsico", psicoService.newPsico());
        modelView.setViewName("newPsico");
        return modelView;
    }

    @GetMapping("/Psicologos")
    public ModelAndView showPsicologos(Authentication authentication) throws Exception{
        ModelAndView modelView = new ModelAndView("psicologos");
        modelView.addObject("listPisco", psicoService.showPsicos());
        if (authentication == null) {
			modelView.addObject("userLogin", false);
        }
        else{
            modelView.addObject("userLogin", true);

			Usuario userDetalis = new Usuario();
			userDetalis = usuarioService.searchUser(authentication.getName());
			String nameLink = userDetalis.getName()+"-"+userDetalis.getLastname();
			modelView.addObject("nameOfUser", nameLink);
            }
        return modelView;
    }
}

