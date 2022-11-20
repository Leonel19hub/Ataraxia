package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Psicologo;
import com.ataraxia.model.Usuario;
import com.ataraxia.service.IPsicologoService;
import com.ataraxia.service.IUsuarioService;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class AdminController {

	private static final Log ATARAXIA = LogFactory.getLog(AdminController.class);

    @Autowired
    IUsuarioService usuarioService;

	@Autowired
	IPsicologoService psicologoService;

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

	@GetMapping("/admin/usuarios/registro-usuario")
	public ModelAndView registerUserFromAdmin(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/registerUser");
		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		// Registrar Usuario USER
		Usuario userUser = new Usuario();
		modelView.addObject("unUsuarioUser", userUser);
		// Registrar Usuario ADMIN
		Usuario userAdmin = new Usuario();
		modelView.addObject("unUsuarioAdmin", userAdmin);
		// Registrar Usuario PSICO
		Psicologo userPsico = new Psicologo();
		modelView.addObject("unUsuarioPsico", userPsico);

		return modelView;
	}

	@PostMapping("/signup-user")
	public ModelAndView saveUserFromAdmin(@Valid @ModelAttribute("unUsuarioAdmin")Usuario usuarioNuevoAdmin, @ModelAttribute("unUsuarioUser")Usuario usuarioNuevoUser, @ModelAttribute("unUsuarioPsico")Psicologo usuarioNuevoPsico, BindingResult resultado, ModelMap model, Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView();

		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);

		if (resultado.hasErrors()) {
			modelView.setViewName("admin/registerUser");
			modelView.addObject("unUsuarioUser", usuarioNuevoUser);
			return modelView;
		}

		try {
			ATARAXIA.info("* Guardando Usuario *");
			usuarioService.saveUser(usuarioNuevoUser);
		} catch (Exception e) {
			modelView.addObject("unUsuarioUser", usuarioService.newUser());
			modelView.setViewName("admin/registerUser");
			return modelView;
		}

		modelView.addObject("unUsuarioUser", usuarioService.newUser());
		modelView.setViewName("admin/registerUser");
		
		return modelView;
	}

	@PostMapping("/signup-psico")
	public ModelAndView savePsicoFromAdmin(@Valid @ModelAttribute("unUsuarioAdmin")Usuario usuarioNuevoAdmin, @ModelAttribute("unUsuarioUser")Usuario usuarioNuevoUser, @ModelAttribute("unUsuarioPsico")Psicologo usuarioNuevoPsico, BindingResult resultado, ModelMap model, Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView();

		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);

		if (resultado.hasErrors()) {
			modelView.setViewName("admin/registerUser");
			modelView.addObject("unUsuarioPsico", usuarioNuevoPsico);
			return modelView;
		}

		try {
			ATARAXIA.info("* Guardando Usuario *");
			psicologoService.savePsicologo(usuarioNuevoPsico);
		} catch (Exception e) {
			modelView.addObject("unUsuarioPsico",psicologoService.newPsico());
			modelView.setViewName("admin/registerUser");
			return modelView;
		}

		modelView.addObject("unUsuarioPsico",psicologoService.newPsico());
		modelView.setViewName("admin/registerUser");
		
		return modelView;
	}

	@PostMapping("/signup-admin")
	public ModelAndView saveAdminFromAdmin(@Valid @ModelAttribute("unUsuarioAdmin")Usuario usuarioNuevoAdmin, @ModelAttribute("unUsuarioUser")Usuario usuarioNuevoUser, @ModelAttribute("unUsuarioPsico")Psicologo usuarioNuevoPsico, BindingResult resultado, ModelMap model, Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView();

		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);

		if (resultado.hasErrors()) {
			modelView.setViewName("admin/registerUser");
			modelView.addObject("unUsuarioAdmin", usuarioNuevoAdmin);
			return modelView;
		}

		try {
			ATARAXIA.info("* Guardando Usuario *");
			usuarioService.saveUserAdmin(usuarioNuevoAdmin);
		} catch (Exception e) {
			modelView.addObject("unUsuarioAdmin", usuarioService.newUser());
			modelView.setViewName("admin/registerUser");
			return modelView;
		}

		modelView.addObject("unUsuarioAdmin", usuarioService.newUser());
		modelView.setViewName("admin/registerUser");
		
		return modelView;
	}


    @GetMapping("/admin/mostrar-usuarios")
	public ModelAndView listUsers(Authentication authentication, Model model) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/mostrarUsuarios");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);

		try {
			// modelView.addObject("listAdmin", usuarioService.listAdmin());
			// modelView.addObject("listUser", usuarioService.listUser());
			model.addAttribute("listAdmin", usuarioService.listAdmin());
			model.addAttribute("listUser", usuarioService.listUser());
			model.addAttribute("listPsico", psicologoService.showPsicos());
		} catch (Exception e) {
			ATARAXIA.fatal("Error: "+e.getMessage());
		}
        
		return modelView;
	}

    @GetMapping("/admin/modificar/{urlUser}")
    public ModelAndView editUserAdminOrUser(@PathVariable(name = "urlUser") String url, Authentication authentication, Model model) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/editUser");
		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		// Buscar usuario a modificar
		Usuario userToEdit = new Usuario();
		try {
			userToEdit = usuarioService.searchUser(url);
		} catch (Exception e) {
			ATARAXIA.info("Error: "+e.getMessage());
		}
		// modelView.addObject("userEdit", userToEdit);
		model.addAttribute("userEdit", userToEdit);
        
        return modelView;
    }

	@PostMapping("/admin/modificar")
	public ModelAndView postEditUserOrAdmin(@ModelAttribute("userF") Usuario modifiedUser, Authentication authentication) throws Exception{
		usuarioService.editUser(modifiedUser);
		ModelAndView modelView = new ModelAndView("admin/editUser");
		// Usurio ADMIN online
		Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		// Usuario userToEdit = new Usuario();
		// userToEdit = usuarioService.searchUser(url);
		// modelView.addObject("userEdit", userToEdit);

		return modelView;
	}
	
	@GetMapping("/admin/eliminarUsuario/{email}")
	public String deleteUser(@PathVariable(name = "email") String email, Model model){
		try {
			Usuario aux = usuarioService.searchUser(email);
			usuarioService.deleteUser(aux.getIdUser());
		} catch (Exception e) {
			ATARAXIA.fatal("Error: "+e.getMessage());
			return "redirect:/modificar/{email}";
		}
		return "redirect:/admin/mostrar-usuarios";
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
