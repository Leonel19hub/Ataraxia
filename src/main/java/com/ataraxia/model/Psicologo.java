package com.ataraxia.model;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.stereotype.Component;

@Entity
@Component
@PrimaryKeyJoinColumn(referencedColumnName = "idUser")
public class Psicologo extends Usuario { 
	
	// private String direccionTrabajo;
	private String provincia;
	private String localidad;
	private String calle;
	private int numero;
	private String titulo;
	
	public Psicologo (){
		super();
	}

	// public String getDireccionTrabajo() {
	// 	return direccionTrabajo;
	// }
	// public void setDireccionTrabajo(String direccionTrabajo) {
	// 	this.direccionTrabajo = direccionTrabajo;
	// }
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	

}
