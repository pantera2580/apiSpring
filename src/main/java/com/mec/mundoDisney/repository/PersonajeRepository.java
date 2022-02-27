package com.mec.mundoDisney.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mec.mundoDisney.dto.custom.PersonajeSimple;
import com.mec.mundoDisney.model.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
	
	@Query(value = "select p.imagen as imagen, p.nombre as nombre from Personaje p")
	public Optional<List<PersonajeSimple>> findAllByPictureAndName();
	
	public boolean existsByNombre(String nombre);
	
	public Personaje findById(long id);
	
	public Optional<List<Personaje>> findByEdad(int edad);
	
	public Optional<List<Personaje>> findByPeso(int peso);
	
	public Optional<Personaje> findByNombre(String nombre);
	
}
