package com.ataraxia.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Psicologo;
import com.ataraxia.service.IPsicologoService;

@Controller
public class PsicologoController {

    private static final Log ATARAXIA = LogFactory.getLog(PsicologoController.class);
    
    @Autowired
    Psicologo nuevoPsico;

    @Autowired
    IPsicologoService psicoService; 
    
    @GetMapping("/registroPsicologo")
    ModelAndView uploadUserPsico(){
        ATARAXIA.info("***** Ingresando al metodo: uploadUserPsico *****");
        ModelAndView modelView = new ModelAndView("newPsico");
        modelView.addObject("unPsico", nuevoPsico);
        return modelView;
    }

    // Despues agreagar codigo para añadir foto
    @PostMapping(value = "/guardarPiscologo")
    public ModelAndView savePsico(@Valid @ModelAttribute ("unPsico") Psicologo psicoNuevo, BindingResult resultado, ModelMap model){
        ModelAndView modelView = new ModelAndView();

        if (resultado.hasErrors()) {
            ATARAXIA.fatal("***** ERRROR DE VALIDACION *****");
            modelView.setViewName("newPsico");
            modelView.addObject("unPsico", psicoNuevo);
            return modelView;
        }

        try {
            psicoService.savePsicologo(psicoNuevo);
        } catch (Exception e) {
            modelView.addObject("psicoErrorMessage", e.getMessage());
            modelView.addObject("unPsico", psicoService.newPsico());
            ATARAXIA.fatal("***** Saliendo del metodo PostMapping: savePsico *****");
            modelView.setViewName("");
            return modelView;
        }

        modelView.addObject("piscoErrorMessage", "Psicologo gusrdado correctamente");
        modelView.setViewName("");
        return modelView;
    }
}