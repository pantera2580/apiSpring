package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="personaje")
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
	
	@ManyToMany
	@JoinTable(
	name = "personaje_pelicula", 
	joinColumns = @JoinColumn(name = "id_personaje"	), 
	inverseJoinColumns = @JoinColumn(name = "id_pelicula"))
	private List<Pelicula> peliculas;
}
