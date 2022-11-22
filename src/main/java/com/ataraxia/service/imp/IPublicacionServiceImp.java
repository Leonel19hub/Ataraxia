package com.ataraxia.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ataraxia.model.Publicacion;
import com.ataraxia.model.Usuario;
import com.ataraxia.service.IPsicologoService;
import com.ataraxia.service.IPublicacionService;
import com.ataraxia.service.IUsuarioService;
import com.ataraxia.repository.PublicacionRepository;

@Service
public class IPublicacionServiceImp implements IPublicacionService{

    @Autowired Publicacion nuevoPost;

    @Autowired PublicacionRepository postRepository;

    @Autowired IUsuarioService usuarioService;

    @Autowired IPsicologoService psicologoService;

    @Override
    public Publicacion newPost() {
        return nuevoPost;
    }

    @Override
    public void savePost(Publicacion post) {
        LocalDateTime timePost = LocalDateTime.now();
        post.setDatePost(timePost);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Integer idPost) {
        postRepository.deleteById(idPost);
    }

    @Override
    public void editPost(Publicacion post) {
        postRepository.save(post);
    }

    @Override
    public List<Publicacion> showAllPost() {
        return (List<Publicacion>) postRepository.findAll();
    }

    @Override
    public List<Publicacion> showPostUser(Integer idUser) {
        List<Publicacion> userListPost = showAllPost();
        List<Usuario> postOfUser = usuarioService.listUser();

        for(int i=0;i<postOfUser.size();i++){
            // if(postOfUser.get(i).get)
        }

        return null;
    }

    @Override
    public List<Publicacion> showPostPsico(Integer idPsico) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Publicacion> showPostAdmin(Integer idAdmin) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Publicacion searchPost(Integer idUser) throws Exception {
        return postRepository.findById(idUser).orElseThrow(()-> new Exception("Post not found"));
    }
    
}
