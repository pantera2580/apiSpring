package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="personaje")
public class Personaje implements Serializable{
	/**
	 * Entidad Personaje
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPersonaje;
	
	private String imagen;
	
	@Column(length = 50)
	private String nombre;
	
	private int edad;
	
	private int peso;
	
	@Column(length = 512)
	String historia;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	@JoinTable(
	name = "personaje_pelicula", 
	joinColumns = @JoinColumn(name = "id_personaje"	), 
	inverseJoinColumns = @JoinColumn(name = "id_pelicula"))
	private Set<Pelicula> peliculas;

	public Personaje() {
		super();
	}

	public Personaje(String imagen, String nombre, int edad, int peso, String historia,
			Set<Pelicula> peliculas) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
	}

	public Personaje(long idPersonaje, String imagen, String nombre, int edad, int peso, String historia,
			Set<Pelicula> peliculas) {
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

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	public void agregarPelicula(Pelicula pelicula) {
		this.peliculas.add(pelicula);
		pelicula.getPersonajes().add(this);
	}
	
	public void eliminarPelicula(Pelicula pelicula) {
		this.peliculas.remove(pelicula);
		pelicula.getPersonajes().remove(this);
	}
}
