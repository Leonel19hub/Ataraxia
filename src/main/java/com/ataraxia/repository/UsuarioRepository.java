package com.ataraxia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ataraxia.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    public Optional<Usuario> findById(Integer idUser);
    public Optional<Usuario> findByEmail(String email);
    
}
