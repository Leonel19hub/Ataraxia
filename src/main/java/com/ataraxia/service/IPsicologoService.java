package com.ataraxia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ataraxia.model.Psicologo;

@Service
public interface IPsicologoService {
    
    public Psicologo newPsico();
    public void savePsicologo(Psicologo psico);
    public void deletePsicologo(Integer idPsico);
    public void editPsico(Psicologo psico);
    public List<Psicologo> showPsicos();
    public Psicologo searchPsico(Integer idPisco) throws Exception;
}
