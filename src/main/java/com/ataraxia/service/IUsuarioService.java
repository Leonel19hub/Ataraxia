package com.ataraxia.service;

import org.springframework.stereotype.Service;

import com.ataraxia.model.Usuario;

import java.util.List;

@Service
public interface IUsuarioService {

    public void saveUser(Usuario user);
    public void deleteUser(Integer idUser);
    public void editUser(Usuario user);
    public List<Usuario> showUsers();
    public Usuario searchUser(Integer idUser) throws Exception;
    
}
