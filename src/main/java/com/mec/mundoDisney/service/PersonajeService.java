package com.mec.mundoDisney.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mec.mundoDisney.dto.PersonajeDTOSimple;
import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.repository.PersonajeRepository;

@Service
public class PersonajeService {
	@Autowired()
	PersonajeRepository personajeRepository;
	
	//guardar
	@Transactional
	public void save(Personaje personaje) {
		personajeRepository.save(personaje);
	}
	
	//listar todos los campos
	public List<Personaje> findAll() {
		return (ArrayList<Personaje>) this.personajeRepository.findAll();
	}
	
	//listar solo imagen y nombre
	@Transactional(readOnly = true)
	public List<PersonajeDTOSimple> findAllByPictureAndName() {
		return this.personajeRepository.findAllByPictureAndName();
	}
	
	//existe por id
	@Transactional(readOnly = true)
	public boolean existsById(long idPersonaje) {
		return personajeRepository.existsById(idPersonaje);
		
	}
	
	//existe por nombre
	@Transactional(readOnly = true)
	public boolean existsByNombre(String nombre) {
		return personajeRepository.existsByNombre(nombre);
	}
	
	//borrar
	@Transactional
	public void delete(long idPersonaje) {
		personajeRepository.deleteById(idPersonaje);
	}
	
	//busqueda por id
	@Transactional(readOnly = true)
	public Personaje findById(long id) {
		return personajeRepository.findById(id);
	}
	
	//busqueda por edad
	@Transactional(readOnly = true)
	public List<Personaje> findByEdad(int edad) {
		return personajeRepository.findByEdad(edad);
	};
	
	//busqueda por peso
	@Transactional(readOnly = true)
	public List<Personaje> findByPeso(int peso) {
		return personajeRepository.findByPeso(peso);
	};
	
	//busqueda por nombre
	@Transactional(readOnly = true)
	public Personaje findByNombre(String nombre) {
		return personajeRepository.findByNombre(nombre);
	};
}
