package com.mec.mundoDisney.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.mec.mundoDisney.dto.PersonajeDTO;
import com.mec.mundoDisney.dto.custom.PersonajeSimple;
import com.mec.mundoDisney.exception.PersonajeAlreadyExistException;
import com.mec.mundoDisney.model.Personaje;

public interface IPersonajeService {

	// guardar
	public void save(PersonajeDTO personajeDTO) throws PersonajeAlreadyExistException;
	
	//actualizar
	public void update(long id, PersonajeDTO personajeDTO) throws NoSuchElementException;
	
	// listar todos los campos
	public List<Personaje> findAll();

	// listar solo imagen y nombre
	public List<PersonajeSimple> findAllByPictureAndName() throws NoSuchElementException;

	// existe por id
	public boolean existsById(long idPersonaje);

	// existe por nombre
	public boolean existsByNombre(String nombre);

	// borrar
	public void delete(long idPersonaje) throws NoSuchElementException;

	// busqueda por id
	public PersonajeDTO findById(long id) throws NoSuchElementException;

	// busqueda por edad
	public List<PersonajeDTO> findByEdad(int edad) throws NoSuchElementException;

	// busqueda por peso
	public List<PersonajeDTO> findByPeso(int peso) throws NoSuchElementException;

	// busqueda por nombre
	public PersonajeDTO findByNombre(String nombre) throws NoSuchElementException;
}
