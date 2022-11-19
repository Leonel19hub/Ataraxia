package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Usuario;
import com.ataraxia.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class AdminController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/admin/dashboard")
	public ModelAndView adminDashboard(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/dashboard");
        // if (authentication.isAuthenticated()) {
            Usuario userOnline = new Usuario();
            userOnline = usuarioService.searchUser(authentication.getName());
            // modelView.addObject("name", userOnline.getName());
            // modelView.addObject("lastname", userOnline.getLastname());
            modelView.addObject("userDetails", userOnline);
        // }
		return modelView;
	}

    @GetMapping("/admin/mostrar-usuarios")
	public ModelAndView listUsers(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/mostrarUsuarios");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);

        modelView.addObject("listAdmin", usuarioService.listAdmin());
        modelView.addObject("listUser", usuarioService.listUser());
        System.out.println("Tamaño de lista de usuarios: "+usuarioService.showUsers().size());
        System.out.println("Tamaño de lista Admin: "+usuarioService.listAdmin().size());
        System.out.println("Tamaño de lista User: "+usuarioService.listUser().size());
        
		return modelView;
	}

    @GetMapping("/admin/modificar/{urlUser}")
    public ModelAndView editUserAdminOrUser(@PathVariable(name = "urlUser") String url, Authentication authentication) throws Exception{
        ModelAndView modelView = new ModelAndView("admin/editUser");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
        
        return modelView;
    }

	@GetMapping("/admin/button")
	public ModelAndView buttonLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/button");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/typography")
	public ModelAndView typographyLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/typography");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/element")
	public ModelAndView elementLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/element");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/widget")
	public ModelAndView widgetLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/widget");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/form")
	public ModelAndView formLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/form");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/table")
	public ModelAndView tableLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/table");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/chart")
	public ModelAndView chartLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/chart");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/signin")
	public ModelAndView siginLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/signin");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/404")
	public ModelAndView errorLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/404");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/signup")
	public ModelAndView singupLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/signup");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}

	@GetMapping("/admin/blank")
	public ModelAndView blankLink(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/blank");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		return modelView;
	}
    
}
