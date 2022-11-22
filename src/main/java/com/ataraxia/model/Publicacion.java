package com.ataraxia.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.stereotype.Component;

@Entity
@Component
// @PrimaryKeyJoinColumn(referencedColumnName = "idUser")
public class Publicacion {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;
    @Lob
    private String imgPost;
    private String descriptionPost;
    private LocalDateTime datePost;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private Usuario usuario;

    public Integer getIdPost() {
        return idPost;
    }
    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }
    public String getDescriptionPost() {
        return descriptionPost;
    }
    public void setDescriptionPost(String descriptionPost) {
        this.descriptionPost = descriptionPost;
    }
    public LocalDateTime getDatePost() {
        return datePost;
    }
    public void setDatePost(LocalDateTime datePost) {
        this.datePost = datePost;
    }
    public String getImgPost() {
        return imgPost;
    }
    public void setImgPost(String imgPost) {
        this.imgPost = imgPost;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
