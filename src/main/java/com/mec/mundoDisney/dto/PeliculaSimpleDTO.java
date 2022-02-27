package com.mec.mundoDisney.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mec.mundoDisney.dto.custom.PeliculaSimple;

public class PeliculaSimpleDTO {
	private String imagen;
	private String titulo;
	private String fecha;
	public PeliculaSimpleDTO(String imagen, String titulo, String fecha) {
		super();
		this.imagen = imagen;
		this.titulo = titulo;
		this.fecha = fecha;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public static PeliculaSimpleDTO EntityToDTO(PeliculaSimple peliculaSimple) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		PeliculaSimpleDTO peliculaSimpleDTO = new PeliculaSimpleDTO(
				peliculaSimple.getImage(),
				peliculaSimple.getTitulo(),
				dateFormat.format(peliculaSimple.getFecha())
				);
		return peliculaSimpleDTO;
	}
}
