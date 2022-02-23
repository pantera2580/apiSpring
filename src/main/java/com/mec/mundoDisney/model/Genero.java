package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.Set;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idGenero;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@NotNull
	private String imagen;
	
	@ManyToMany(mappedBy = "generos", cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	private Set<Pelicula> peliculas;
	
}	


