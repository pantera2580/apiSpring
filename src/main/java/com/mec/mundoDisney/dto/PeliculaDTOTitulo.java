package com.mec.mundoDisney.dto;

import java.util.ArrayList;
import java.util.List;

import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.model.Personaje;

public class PeliculaDTOTitulo {
	
	private long idPelicula;
	private String titulo;
	private List<String> personajes;
	
	public PeliculaDTOTitulo() {
		super();
	}

	public PeliculaDTOTitulo(long idPelicula, String titulo, List<String> personajes) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.personajes = personajes;
	}

	public long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<String> personajes) {
		this.personajes = personajes;
	}
	
	public static PeliculaDTOTitulo EntityToDTO(Pelicula pelicula) {
		List<String> personajes = new ArrayList<>();
		for(Personaje personaje: pelicula.getPersonajes()) {
			personajes.add(personaje.getNombre());
		}
		PeliculaDTOTitulo peliculaDTO = new PeliculaDTOTitulo(
				pelicula.getIdPelicula(),
				pelicula.getTitulo(),
				personajes);
		return peliculaDTO;
	}
}
