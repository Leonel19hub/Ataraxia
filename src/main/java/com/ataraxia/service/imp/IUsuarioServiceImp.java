package com.ataraxia.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ataraxia.model.Usuario;
import com.ataraxia.repository.UsuarioRepository;
import com.ataraxia.service.IUsuarioService;

@Service
public class IUsuarioServiceImp implements IUsuarioService{

    @Autowired
    Usuario nuevoUsuario;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void saveUser(Usuario user) {
        String pw = user.getPassword();
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
    	user.setPassword(encoder.encode(pw));
        user.setTipeUser("USER");
        usuarioRepository.save(user);
    }

    @Override
    public void deleteUser(Integer idUser) {
        usuarioRepository.delete(null);
    }

    @Override
    public void editUser(Usuario user) {
        usuarioRepository.save(user);
    }

    @Override
    public List<Usuario> showUsers() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario searchUser(Integer idUser) throws Exception {
        Usuario userFound = new Usuario();
        userFound = usuarioRepository.findById(idUser).orElseThrow(()-> new Exception("User not found"));
        return userFound;
    }

    @Override
    public Usuario newUser() {
        return nuevoUsuario;
    }
    
}
