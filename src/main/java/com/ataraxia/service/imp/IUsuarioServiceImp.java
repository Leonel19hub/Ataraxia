package com.ataraxia.service.imp;

import java.util.ArrayList;
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
        user.setStatusUser(true);
        usuarioRepository.save(user);
    }

    @Override
    public void deleteUser(String email) throws Exception {
        Usuario aux = new Usuario();
        aux = searchUser(email);
        aux.setStatusUser(false);
        usuarioRepository.save(aux);
    }

    @Override
    public void editUser(Usuario user) {
        usuarioRepository.save(user);
    }

    @Override
    public List<Usuario> showUsers() {
        List<Usuario> aux = new ArrayList<>();
        aux = (List<Usuario>) usuarioRepository.findAll();
        List<Usuario> aux2 = new ArrayList<>();

        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getStatusUser()) {
                aux2.add(aux.get(i));
            }
        }

        return aux2;
    }

    @Override
    public Usuario searchUser(String email) throws Exception {
        Usuario userFound = new Usuario();
        userFound = usuarioRepository.findByEmail(email).orElseThrow(()-> new Exception("User not found"));
        return userFound;
    }

    @Override
    public Usuario newUser() {
        return nuevoUsuario;
    }
    
}
