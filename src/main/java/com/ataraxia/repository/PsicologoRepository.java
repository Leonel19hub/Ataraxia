package com.ataraxia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ataraxia.model.Psicologo;

@Repository
public interface PsicologoRepository extends CrudRepository <Integer, Psicologo>{
    
    public Optional<Psicologo> findById(Integer idPsicologo);
    public Optional<Psicologo> findByEmail(String email);
}
