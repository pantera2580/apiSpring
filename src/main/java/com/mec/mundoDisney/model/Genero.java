package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="genero")
public class Genero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idGenero;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@NotNull
	private String imagen;
	
	@ManyToMany(mappedBy = "generos")
	private List<Pelicula> peliculas;
	
}	


