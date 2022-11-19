package com.ataraxia.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ataraxia.controller.PsicologoController;
import com.ataraxia.model.Psicologo;
import com.ataraxia.repository.PsicologoRepository;
import com.ataraxia.service.IPsicologoService;

@Service
public class IPscilogogoServiceImp implements IPsicologoService{
    
    private static final Log ATARAXIA = LogFactory.getLog(PsicologoController.class);

    @Autowired
    Psicologo nuevoPsico;

    @Autowired
    PsicologoRepository psicologoRepository;

    @Override
    public void savePsicologo(Psicologo psico) {
        ATARAXIA.info("***** Encriptando Contraseña *****");
        String pw = psico.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        psico.setPassword(encoder.encode(pw));
        psico.setTipeUser("PSICO");
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
