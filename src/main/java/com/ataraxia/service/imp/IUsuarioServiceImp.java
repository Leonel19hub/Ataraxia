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
        // user.setStatusUser(true);
        usuarioRepository.save(user);
    }

    @Override
    public void deleteUser(Integer idUser) throws Exception {
        // Usuario aux = new Usuario();
        // aux = searchUser(email);
        // aux.setStatusUser(false);
        // usuarioRepository.save(aux);
        usuarioRepository.deleteById(idUser);
    }

    @Override
    public void editUser(Usuario user) {
        usuarioRepository.save(user);
    }

    @Override
    public List<Usuario> showUsers() {
        // List<Usuario> aux = new ArrayList<>();
        // aux = (List<Usuario>) usuarioRepository.findAll();
        // List<Usuario> aux2 = new ArrayList<>();

        // for (int i = 0; i < aux.size(); i++) {
        //     if (aux.get(i).getStatusUser()) {
        //         aux2.add(aux.get(i));
        //     }
        // }

        // return aux2;
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listAdmin() {
        List<Usuario> adminList = new ArrayList<>();
        adminList = (List<Usuario>) usuarioRepository.findAll();
        List<Usuario> auxAdmin = new ArrayList<>();

        for(int i=0; i < adminList.size();i++){
            // if (adminList.get(i).getStatusUser()) {
                if (adminList.get(i).getTipeUser().equals("ADMIN")) {
                    auxAdmin.add(adminList.get(i));
                }
            // }
        }

        return auxAdmin;
    }

    @Override
    public List<Usuario> listUser() {
        List<Usuario> userList = new ArrayList<>();
        userList = (List<Usuario>) usuarioRepository.findAll();
        List<Usuario> auxUser = new ArrayList<>();

        for(int i=0; i < userList.size();i++){
            // if (userList.get(i).getStatusUser()) {
                if (userList.get(i).getTipeUser().equals("USER")) {
                    auxUser.add(userList.get(i));
                }
            // }
        }

        return auxUser;
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

    @Override
    public void saveUserAdmin(Usuario admin) {
        String pw = admin.getPassword();
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
    	admin.setPassword(encoder.encode(pw));
        admin.setTipeUser("ADMIN");
        // admin.setStatusUser(true);
        usuarioRepository.save(admin);
    }

    @Override
    public Usuario searchUserById(Integer idUser) throws Exception {
        return usuarioRepository.findById(idUser).orElseThrow(()-> new Exception("Usuer not found by id"));
    }

    
}
