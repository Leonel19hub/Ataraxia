package com.ataraxia.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Publicacion {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;
    private String descriptionPost;
    private LocalDateTime datePost;

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

    
}
