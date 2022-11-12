package com.ataraxia.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ataraxia.model.Psicologo;
import com.ataraxia.repository.PsicologoRepository;
import com.ataraxia.service.IPsicologoService;

@Service
public class IPscilogogoServiceImp implements IPsicologoService{

    @Autowired
    Psicologo nuevoPsico;

    @Autowired
    PsicologoRepository psicologoRepository;

    @Override
    public void savePsicologo(Psicologo psico) {
        String pw = psico.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        psico.setPassword(encoder.encode(pw));
        psico.setTipeUser("USERPSICO");
        psicologoRepository.save(psico);
    }

    @Override
    public void deletePsicologo(Integer idPsico) {
        // Encontrar una manera de eliminar el usuarioPsicologo usando delete
        psicologoRepository.delete(null);
    }

    @Override
    public void editPsico(Psicologo psico) {
        psicologoRepository.save(psico);
    }

    @Override
    public List<Psicologo> showPsicos() {
        return (List<Psicologo>) psicologoRepository.findAll();
    }

    @Override
    public Psicologo searchPsico(Integer idPisco) throws Exception {
        Psicologo psicoFound = new Psicologo();
        psicoFound = psicologoRepository.findById(idPisco).orElseThrow(()-> new Exception("User not found"));
        return psicoFound;
    }

    @Override
    public Psicologo newPsico() {
        return nuevoPsico;
    }
    
}
