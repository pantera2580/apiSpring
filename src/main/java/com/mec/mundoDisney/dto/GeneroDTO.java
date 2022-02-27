package com.mec.mundoDisney.dto;

import java.util.ArrayList;
import java.util.List;

import com.mec.mundoDisney.model.Genero;
import com.mec.mundoDisney.model.Pelicula;

public class GeneroDTO {
	
	private long idGenero;
	
	private String nombre;
	
	private String imagen;
	
	private List<String> peliculas;

	public GeneroDTO() {
		super();
	}
	
	
	public GeneroDTO(String nombre, String imagen, List<String> peliculas) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
		this.peliculas = peliculas;
	}



	public GeneroDTO(long idGenero, String nombre, String imagen, List<String> peliculas) {
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


	public List<String> getPeliculas() {
		return peliculas;
	}


	public void setPeliculas(List<String> peliculas) {
		this.peliculas = peliculas;
	}
	
	public static GeneroDTO EntityToDTO(Genero genero) {
		List<String> peliculas = new ArrayList<>();
		for(Pelicula pelicula: genero.getPeliculas()) {
			peliculas.add(pelicula.getTitulo());
		}
		GeneroDTO generoDTO = new GeneroDTO(
		genero.getIdGenero(),
		genero.getNombre(),
		genero.getImagen(),
		peliculas);
		return generoDTO;
	}
	
}
