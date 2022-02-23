package com.mec.mundoDisney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.repository.PeliculaRepository;

@Service
public class PeliculaService {
	@Autowired
	PeliculaRepository peliculaRepository;
	
	//buscar una pelicula
	@Transactional(readOnly = true)
	public Pelicula findByTitulo(String titulo) {
		return peliculaRepository.findByTitulo(titulo);
	}
	
	//salvar una pelicula
	@Transactional
	public void save(Pelicula pelicula) {
		peliculaRepository.save(pelicula);
	}
	//buscar por id
	@Transactional(readOnly = true)
	public Pelicula findById(long id) {
		return peliculaRepository.findByIdPelicula(id);
	}
}

