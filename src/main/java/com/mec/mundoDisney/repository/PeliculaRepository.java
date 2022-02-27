package com.mec.mundoDisney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mec.mundoDisney.dto.custom.PeliculaSimple;
import com.mec.mundoDisney.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
	
	public Optional<Pelicula> findByTitulo(String titulo);
		
	@Query(value = "select p.imagen as imagen, p.titulo as titulo, p.fecha as fecha from Pelicula p")
	public Optional<List<PeliculaSimple>> findAllBy();
	
	public boolean existsById(long idPelicula);
	
	public boolean existsByTitulo(String titulo);
	
	@Query(value = "select p.imagen as imagen, p.titulo as titulo, p.fecha as fecha from Pelicula p order by p.titulo asc")
	public Optional<List<PeliculaSimple>> findByOrderByTituloAsc();
	
	@Query(value = "select p.imagen as imagen, p.titulo as titulo, p.fecha as fecha from Pelicula p order by p.titulo desc")
	public Optional<List<PeliculaSimple>> findByOrderByTituloDesc();
	
}
