package com.ataraxia.service;

import org.springframework.stereotype.Service;

import com.ataraxia.model.Usuario;

import java.util.List;

@Service
public interface IUsuarioService {

    public Usuario newUser();
    public void saveUser(Usuario user);
    public void saveUserAdmin(Usuario admin);
    public void deleteUser(Integer idUser) throws Exception;
    public void editUser(Usuario user);
    public List<Usuario> showUsers();
    public List<Usuario> listAdmin();
    public List<Usuario> listUser();
    public Usuario searchUser(String email) throws Exception;
    
}
