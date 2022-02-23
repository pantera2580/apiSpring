package com.mec.mundoDisney.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.model.Personaje;


public class PersonajeDTO {

	private long idPersonaje;
	
	@NotBlank
	private String imagen;
	
	@NotBlank
	private String nombre;
	
	@Min(0)
	private int edad;
	
	@Min(0)
	private int peso;
	
	@NotBlank
	String historia;
	
	@NotEmpty
	private List<String> peliculas;

	public PersonajeDTO() {
		super();
	}

	public PersonajeDTO(@NotBlank String imagen, @NotBlank String nombre, @Min(0) int edad, @Min(0) int peso,
			@NotBlank String historia, @NotEmpty List<String> peliculas) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
	}

	public PersonajeDTO(long idPersonaje, @NotBlank String imagen, @NotBlank String nombre, @Min(0) int edad,
			@Min(0) int peso, @NotBlank String historia, @NotEmpty List<String> peliculas) {
		super();
		this.idPersonaje = idPersonaje;
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
	}

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

	public List<String> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<String> peliculas) {
		this.peliculas = peliculas;
	}
	
	public static Personaje DtoToEntity(long id, PersonajeDTO personajeDTO, Set<Pelicula> peliculas) {
		Personaje personaje = new Personaje(
				id,
				personajeDTO.getImagen(), 
				personajeDTO.getNombre(), 
				personajeDTO.getEdad(),
				personajeDTO.getPeso(),
				personajeDTO.getHistoria(),
				peliculas
				);
		return personaje;
	}
	public static Personaje DtoToEntity(PersonajeDTO personajeDTO, Set<Pelicula> peliculas) {
		Personaje personaje = new Personaje(
				personajeDTO.getImagen(), 
				personajeDTO.getNombre(), 
				personajeDTO.getEdad(),
				personajeDTO.getPeso(),
				personajeDTO.getHistoria(),
				peliculas
				);
		return personaje;
	}
	
	public static PersonajeDTO EntityToDto(Personaje personaje) {
		List<String> peliculas = new ArrayList<String>();
		for(Pelicula pelicula: personaje.getPeliculas()) {
			peliculas.add(pelicula.getTitulo());
		}
		PersonajeDTO personajeDTO = new PersonajeDTO(
				personaje.getIdPersonaje(), 
				personaje.getImagen(),
				personaje.getNombre(),
				personaje.getEdad(),
				personaje.getPeso(),
				personaje.getHistoria(),
				peliculas);
		return personajeDTO;
	}
}
