package com.ataraxia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ataraxia.model.Publicacion;

@Repository
public interface PublicacionRepository  extends CrudRepository<Publicacion, Integer>{

    public Optional<Publicacion> findById(Integer id);
}
