package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PsicologoController {
    
    @GetMapping("/tipo-registro")
    public String tipoRegistro(){
        return "pre-registro";
    }
    
}
