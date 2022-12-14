package com.ataraxia.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ataraxia.model.Usuario;
import com.ataraxia.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService{
    
    @Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//busqueda del usuario
		// Usuario usuarioEncontrado = usuarioRepository.findById(Integer.parseInt(dni)).orElseThrow(()->new UsernameNotFoundException("Usuario Invalido"));
		Usuario usuarioEncontrado = usuarioRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usser not found"));
		
		//definir autorizaciones
		List <GrantedAuthority> tipos = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getTipeUser());
		tipos.add(grantedAuthority);
		
		//definir el usuario en sesion

		UserDetails usuarioEnSesion = new User(email,usuarioEncontrado.getPassword(),tipos);
		
		return usuarioEnSesion;
	}
}
