package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="genero")
public class Genero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idGenero;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = true)
	private String imagen;
	
	@ManyToMany(mappedBy = "generos", cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	private Set<Pelicula> peliculas;

	public Genero() {
		super();
	}

	public Genero(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Genero(long idGenero, String nombre, String imagen, Set<Pelicula> peliculas) {
		super();
		this.idGenero = idGenero;
		this.nombre = nombre;
		this.imagen = imagen;
		this.peliculas = peliculas;
	}

	public long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}	


