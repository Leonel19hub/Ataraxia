package com.ataraxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ataraxia.model.Psicologo;
import com.ataraxia.model.Usuario;
import com.ataraxia.service.IPsicologoService;
import com.ataraxia.service.IUsuarioService;

import java.util.Base64;

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

	@PostMapping(value = "/signup-user", consumes = "multipart/form-data")
	public ModelAndView saveUserFromAdmin(@Valid @ModelAttribute("unUsuarioAdmin")Usuario usuarioNuevoAdmin, @ModelAttribute("unUsuarioUser")Usuario usuarioNuevoUser, @ModelAttribute("unUsuarioPsico")Psicologo usuarioNuevoPsico, BindingResult resultado, ModelMap model, Authentication authentication, @RequestParam("fileUser") MultipartFile fileUser) throws Exception{
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
			byte[] content = fileUser.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			usuarioNuevoUser.setAvatar(base64);
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

	@PostMapping(value = "/signup-psico", consumes = "multipart/form-data")
	public ModelAndView savePsicoFromAdmin(@Valid @ModelAttribute("unUsuarioAdmin")Usuario usuarioNuevoAdmin, @ModelAttribute("unUsuarioUser")Usuario usuarioNuevoUser, @ModelAttribute("unUsuarioPsico")Psicologo usuarioNuevoPsico, BindingResult resultado, ModelMap model, Authentication authentication, @RequestParam("filePsico") MultipartFile filePsico) throws Exception{
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
			byte[] content = filePsico.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			usuarioNuevoPsico.setAvatar(base64);
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

	@PostMapping(value = "/signup-admin", consumes = "multipart/form-data")
	public ModelAndView saveAdminFromAdmin(@Valid @ModelAttribute("unUsuarioAdmin")Usuario usuarioNuevoAdmin, @ModelAttribute("unUsuarioUser")Usuario usuarioNuevoUser, @ModelAttribute("unUsuarioPsico")Psicologo usuarioNuevoPsico, BindingResult resultado, ModelMap model, Authentication authentication, @RequestParam("fileAdmin") MultipartFile fileAdmin) throws Exception{
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
			byte[] content = fileAdmin.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			usuarioNuevoAdmin.setAvatar(base64);
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
	public ModelAndView listUsers(Authentication authentication) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/mostrarUsuarios");

        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);

		try {
			modelView.addObject("listAdmin", usuarioService.listAdmin());
			modelView.addObject("listUser", usuarioService.listUser());
			modelView.addObject("listPsico", psicologoService.showPsicos());
			ATARAXIA.info("Cantidad de psicologos: "+psicologoService.showPsicos().size());
			// model.addAttribute("listAdmin", usuarioService.listAdmin());
			// model.addAttribute("listUser", usuarioService.listUser());
			// model.addAttribute("listPsico", psicologoService.showPsicos());
		} catch (Exception e) {
			ATARAXIA.fatal("Error: "+e.getMessage());
		}
        
		return modelView;
	}

	Usuario userAux = new Usuario();

    @GetMapping("/admin/modificar/{idUser}")
    public ModelAndView editUserAdminOrUser(@PathVariable(name = "idUser") Integer id, Authentication authentication, Model model) throws Exception{
		ModelAndView modelView = new ModelAndView("admin/editUser");
		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
        modelView.addObject("userDetails", userOnline);
		// Buscar usuario a modificar
		Usuario userToEdit = new Usuario();
		Usuario userDetailsInput = new Usuario();
		try {
			userToEdit = usuarioService.searchUserById(id);
			userDetailsInput = usuarioService.searchUserById(id);
			ATARAXIA.info("Usuario encontrado: "+userToEdit.getEmail());
		} catch (Exception e) {
			ATARAXIA.info("Error: "+e.getMessage());
		}
		// modelView.addObject("userEdit", userToEdit);
		model.addAttribute("userEdit", userToEdit);
		model.addAttribute("userInput", userDetailsInput);
		model.addAttribute("hidden", true);
		userAux = userToEdit;
        
        return modelView;
    }

	// @PostMapping("/admin/modificar")
	// public ModelAndView postEditUserOrAdmin(@ModelAttribute("userF") Usuario modifiedUser, Authentication authentication) throws Exception{
	// 	ATARAXIA.info("UserEdit: "+userAux.getName()+" "+userAux.getUserName());
	// 	ATARAXIA.info("UserEdit: "+modifiedUser.getName()+" "+modifiedUser.getUserName()+" "+modifiedUser.getIdUser());
	// 	usuarioService.editUser(modifiedUser);
	// 	ModelAndView modelView = new ModelAndView("admin/dashboard");

	// 	Usuario userOnline = new Usuario();
    //     userOnline = usuarioService.searchUser(authentication.getName());
    //     modelView.addObject("userDetails", userOnline);

	// 	return modelView;
	// }

	@PostMapping("/admin/modificar")
	public String postEditUserorAdminString(@ModelAttribute("userF") Usuario modifiedUser, Authentication authentication, Model model) throws Exception{
		// Usario ADMIN online
        Usuario userOnline = new Usuario();
        userOnline = usuarioService.searchUser(authentication.getName());
		model.addAttribute("userDetails", userOnline);

		model.addAttribute("listAdmin", usuarioService.listAdmin());
		model.addAttribute("listUser", usuarioService.listUser());
		model.addAttribute("listPsico", psicologoService.showPsicos());

		model.addAttribute("hidden", true);

		usuarioService.editUser(modifiedUser);

		return "redirect:/admin/mostrar-usuarios";
	}
	
	@GetMapping("/admin/eliminarUsuario/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id, Model model){
		try {
			// Usuario aux = usuarioService.searchUserById(id);
			usuarioService.deleteUser(id);
		} catch (Exception e) {
			ATARAXIA.fatal("Error: "+e.getMessage());
			return "redirect:/modificar/{id}";
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
