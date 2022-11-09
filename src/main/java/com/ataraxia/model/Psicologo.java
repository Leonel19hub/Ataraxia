package com.ataraxia.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Psicologo extends Usuario {
	
	private String direccionTrabajo;
	private String titulo;
	
	
	public String getDireccionTrabajo() {
		return direccionTrabajo;
	}
	public void setDireccionTrabajo(String direccionTrabajo) {
		this.direccionTrabajo = direccionTrabajo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
