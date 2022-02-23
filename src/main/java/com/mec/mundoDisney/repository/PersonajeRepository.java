package com.mec.mundoDisney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mec.mundoDisney.dto.PersonajeDTOSimple;
import com.mec.mundoDisney.model.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
	
	@Query(value = "select p.imagen as imagen, p.nombre as nombre from Personaje p")
	public List<PersonajeDTOSimple> findAllByPictureAndName();
	
	public boolean existsByNombre(String nombre);
	
	public Personaje findById(long id);
	
	public List<Personaje> findByEdad(int edad);
	
	public List<Personaje> findByPeso(int peso);
	
	public Personaje findByNombre(String nombre);
	
	
}
