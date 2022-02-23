package com.mec.mundoDisney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mec.mundoDisney.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
	
	public Pelicula findByTitulo(String titulo);
	
	public Pelicula findByIdPelicula(long id);
}
