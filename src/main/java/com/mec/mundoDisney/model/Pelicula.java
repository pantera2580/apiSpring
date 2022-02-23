package com.mec.mundoDisney.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="pelicula")
public class Pelicula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPelicula;
	
	@Column(nullable = false, length = 50)
	private String titulo;
	
	@Column(columnDefinition = "DATE")
	private Date fecha;
	
	private int calificacion;
	
	private String imagen;
	
	@ManyToMany(mappedBy = "peliculas", cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	private List<Personaje> personajes;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	@JoinTable(
	name = "pelicula_genero", 
	joinColumns = @JoinColumn(name = "id_pelicula"	), 
	inverseJoinColumns = @JoinColumn(name = "id_genero"))
	private List<Genero> generos;

	public Pelicula() {
		super();
	}

	public Pelicula(String titulo) {
		super();
		this.titulo = titulo;
	}

	public Pelicula(String titulo, Date fecha, int calificacion, String imagen, List<Personaje> personajes,
			List<Genero> generos) {
		super();
		this.titulo = titulo;
		this.fecha = fecha;
		this.calificacion = calificacion;
		this.imagen = imagen;
		this.personajes = personajes;
		this.generos = generos;
	}

	public Pelicula(long idPelicula, String titulo, Date fecha, int calificacion, String imagen,
			List<Personaje> personajes, List<Genero> generos) {
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	
	
	
}	


