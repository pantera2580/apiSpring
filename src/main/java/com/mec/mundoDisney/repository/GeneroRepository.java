package com.mec.mundoDisney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mec.mundoDisney.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long>{
	public Genero findByNombre(String nombre);
	
	public boolean existsByNombre(String nombre);
}
