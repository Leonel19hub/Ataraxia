package com.ataraxia.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Publicacion;
import com.ataraxia.model.Usuario;
import com.ataraxia.service.IPublicacionService;
import com.ataraxia.service.IUsuarioService;

@Controller
public class PublicacionController {

    @Autowired IPublicacionService postService;

    @Autowired IUsuarioService usuarioService;
    
    @PostMapping(value = "/publicar-post", consumes = "multipart/form-data")
    public ModelAndView uploadPostContent(@Valid @ModelAttribute("postContent") Publicacion postNew, @RequestParam("post") MultipartFile filePost, Authentication authentication) throws Exception{
        ModelAndView modelView = new ModelAndView();
        // Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
        // Setera el id de usuario en la tabla Publicacion
        Usuario uderIdPost = usuarioService.searchUser(authentication.getName());
        try {
            byte[] content = filePost.getBytes();
            String base64 = Base64.getEncoder().encodeToString(content);
            postNew.setImgPost(base64);
            postNew.setUsuario(uderIdPost);
            postService.savePost(postNew);
        } catch (Exception e) {
            modelView.addObject("postContent", postService.newPost());
            modelView.setViewName("contenido");
        }

        modelView.addObject("postContent", postService.newPost());
        modelView.setViewName("contenido");

        return modelView;
    }
}
