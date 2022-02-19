package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="pelicula")
public class Pelicula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPelicula;
	
	@Column(nullable = false, length = 50)
	private String titulo;
	
	@Column(nullable = false, columnDefinition = "DATE")
	private Date fecha;
	
	@Column(nullable = false)
	private int calificacion;
	
	@NotNull
	private String imagen;
	
	@ManyToMany(mappedBy = "peliculas")
	private List<Personaje> personajes;
	
	@ManyToMany
	@JoinTable(
	name = "pelicula_genero", 
	joinColumns = @JoinColumn(name = "id_pelicula"	), 
	inverseJoinColumns = @JoinColumn(name = "id_genero"))
	private List<Genero> generos;
}	


