package com.mec.mundoDisney.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personajes")
public class Personaje implements Serializable{
	/**
	 * Entidad Personaje
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPersonaje;
	
	private String imagen;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false)
	private int edad;
	
	@Column(nullable = false)
	private int peso;
	
	@Column(nullable = false, length = 512)
	String historia;

	public long getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
	
}
