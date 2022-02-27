package com.mec.mundoDisney.dto;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.model.Genero;

public class PeliculaDTO {
	
	private long idPelicula;
	
	@NotBlank(message = "El campo titulo no puede estar vacio")
	private String titulo;
	
	private String fecha;
	@Min(0)
	@Max(10)
	private int calificacion;
	@NotBlank(message = "El campo imagen no puede estar vacio")
	private String imagen;
	
	private List<String> personajes;
	
	private List<String> generos;

	public PeliculaDTO() {
		super();
	}

	public PeliculaDTO(@NotBlank(message = "El campo titulo no puede estar vacio") String titulo, String fecha,
			@Min(0) int calificacion, @NotBlank(message = "El campo imagen no puede estar vacio") String imagen,
			List<String> personajes, List<String> generos) {
		super();
		this.titulo = titulo;
		this.fecha = fecha;
		this.calificacion = calificacion;
		this.imagen = imagen;
		this.personajes = personajes;
		this.generos = generos;
	}

	public PeliculaDTO(long idPelicula, @NotBlank(message = "El campo titulo no puede estar vacio") String titulo,
			String fecha, @Min(0) int calificacion,
			@NotBlank(message = "El campo imagen no puede estar vacio") String imagen, List<String> personajes,
			List<String> generos) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.fecha = fecha;
		this.calificacion = calificacion;
		this.imagen = imagen;
		this.personajes = personajes;
		this.generos = generos;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<String> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<String> personajes) {
		this.personajes = personajes;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}
	
	public static Pelicula DtoToEntity(long id, PeliculaDTO peliculaDTO, Set<Personaje> personajes, Set<Genero> generos) throws ParseException {
		Date fechaFormato;
		if(peliculaDTO.getFecha()!=null && peliculaDTO.getFecha()!="") {
			fechaFormato = new SimpleDateFormat("dd/MM/yyyy").parse(peliculaDTO.getFecha());
		}
		else {
			fechaFormato = new Date();
		}
		Pelicula pelicula = new Pelicula(
				id,
				peliculaDTO.getTitulo(), 
				fechaFormato,
				peliculaDTO.getCalificacion(),
				peliculaDTO.getImagen(), 
				personajes,
				generos
				);
		return pelicula;
	}
	public static Pelicula DtoToEntity(PeliculaDTO peliculaDTO, Set<Personaje> personajes, Set<Genero> generos) throws ParseException {
		Date fechaFormato;
		if(peliculaDTO.getFecha()!=null && peliculaDTO.getFecha()!="") {
			fechaFormato = new SimpleDateFormat("dd/MM/yyyy").parse(peliculaDTO.getFecha());
		}
		else {
			fechaFormato = new Date();
		}
		Pelicula pelicula = new Pelicula(
				peliculaDTO.getTitulo(), 
				fechaFormato,
				peliculaDTO.getCalificacion(),
				peliculaDTO.getImagen(), 
				personajes,
				generos
				);
		return pelicula;
	}
	
	public static PeliculaDTO EntityToDto(Pelicula pelicula) {
		List<String> personajes = new ArrayList<String>();
		List<String> generos = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		for(Personaje personaje: pelicula.getPersonajes()) {
			personajes.add(personaje.getNombre());
		}
		for(Genero genero: pelicula.getGeneros()) {
			generos.add(genero.getNombre());
		}
		PeliculaDTO peliculaDTO = new PeliculaDTO(
				pelicula.getIdPelicula(), 
				pelicula.getTitulo(),
				dateFormat.format(pelicula.getFecha()),
				pelicula.getCalificacion(),
				pelicula.getImagen(),
				personajes,
				generos);
		return peliculaDTO;
	}
	
}
