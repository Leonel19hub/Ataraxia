package com.ataraxia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ataraxia.model.Publicacion;

@Service
public interface IPublicacionService {
    
    public Publicacion newPost();
    public void savePost(Publicacion post);
    public void deletePost(Integer idPost);
    public void editPost(Publicacion post);
    public List<Publicacion> showAllPost();
    public List<Publicacion> showPostUser(Integer idUser);
    public List<Publicacion> showPostPsico(Integer idPsico);
    public List<Publicacion> showPostAdmin(Integer idAdmin);
    public Publicacion searchPost(Integer idUser) throws Exception;
}
