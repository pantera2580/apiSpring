package com.mec.mundoDisney.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.repository.PersonajeDao;

@Service
public class PersonajeService {
	@Autowired()
	PersonajeDao personajeDao;
	
	public List<Personaje> listarPersonajes() {
		return (ArrayList<Personaje>) this.personajeDao.findAll();
	}
	
	public boolean existsById(long idPersonaje) {
		return personajeDao.existsById(idPersonaje);
	}
	
	
	public void delete(long idPersonaje) {
		personajeDao.deleteById(idPersonaje);
	}
	
}
