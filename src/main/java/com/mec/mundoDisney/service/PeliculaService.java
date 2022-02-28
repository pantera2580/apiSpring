package com.mec.mundoDisney.service;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mec.mundoDisney.dto.PeliculaDTO;
import com.mec.mundoDisney.dto.PeliculaSimpleDTO;
import com.mec.mundoDisney.dto.custom.PeliculaSimple;
import com.mec.mundoDisney.exception.PeliculaAlreadyExistException;
import com.mec.mundoDisney.model.Genero;
import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.repository.GeneroRepository;
import com.mec.mundoDisney.repository.PeliculaRepository;
import com.mec.mundoDisney.repository.PersonajeRepository;

@Service
public class PeliculaService implements IPeliculaService{
	@Autowired
	PeliculaRepository peliculaRepository;
	
	@Autowired
	PersonajeRepository personajeRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	//buscar una pelicula
	@Transactional(readOnly = true)
	@Override
	public PeliculaDTO findByTitulo(String titulo) throws NoSuchElementException{
		Pelicula pelicula = peliculaRepository.findByTitulo(titulo).orElseThrow(() -> new NoSuchElementException(MessageFormat.format("PELICULA_TITLE_NOT_FOUND", titulo)));
		return PeliculaDTO.EntityToDto(pelicula);
	}
	
	// salvar una pelicula
	@Transactional
	@Override
	public void save(PeliculaDTO peliculaDTO) throws PeliculaAlreadyExistException, ParseException {
		if(peliculaRepository.existsByTitulo(peliculaDTO.getTitulo())) 
			throw new PeliculaAlreadyExistException(peliculaDTO.getTitulo());
		else {
			Set<Personaje> personajes = new HashSet<>();
			// verifica si los personajes asociados existen, sino los crea
			for (String nombre : peliculaDTO.getPersonajes()) {
				if (personajeRepository.existsByNombre(nombre)) {
					personajes.add(personajeRepository.findByNombre(nombre).get());
				} else {
					// crear entidad
					Personaje personaje = new Personaje(nombre);
					// salvar entidad
					personajeRepository.save(personaje);
					// agregar a la lista de peliculas relacionadas
					personajes.add(personaje);
				}
			}
			// verifica si los generos asociados existen, sino los crea
			Set<Genero> generos = new HashSet<>();
			for (String nombre : peliculaDTO.getGeneros()) {
				if (generoRepository.existsByNombre(nombre)) {
					// agrega la entidad a la lista de personajes relacionados
					generos.add(generoRepository.findByNombre(nombre));
				} else {
					// crear entidad
					Genero genero = new Genero(nombre);
					// salvar entidad
					generoRepository.save(genero);
					// agregar a la lista de peliculas relacionadas
					generos.add(genero);
				}
			}
		// mapear el dto a entidad
		Pelicula pelicula = PeliculaDTO.DtoToEntity(peliculaDTO, personajes, generos);
		peliculaRepository.save(pelicula);
		}
	}
		
	//buscar por id
	@Transactional(readOnly = true)
	@Override
	public Pelicula findById(long id) throws NoSuchElementException {
		return peliculaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(MessageFormat.format("PELICULA_ID_NOT_FOUND", id)));
	}	
	
	//buscar todos
	@Transactional(readOnly = true)
	@Override
	public List<PeliculaSimpleDTO> findAllBy() {
		List<PeliculaSimple> peliculas =  peliculaRepository.findAllBy().orElseThrow(() -> new NoSuchElementException(MessageFormat.format("PELICULA_ID_NOT_FOUND", "null")));
		List<PeliculaSimpleDTO> peliculasDTO = new ArrayList<>();
		for (PeliculaSimple peliculaSimple : peliculas) {
			PeliculaSimpleDTO peliculaSimpleDTO = PeliculaSimpleDTO.EntityToDTO(peliculaSimple);
			peliculasDTO.add(peliculaSimpleDTO);
		}
		return peliculasDTO;
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean existsById(long id) {
		return peliculaRepository.existsById(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean existsByTitulo(String titulo) {
		return peliculaRepository.existsByTitulo(titulo);
	}

	@Override
	public void delete(long id) throws NoSuchElementException {
		if(!peliculaRepository.existsById(id)) throw new NoSuchElementException(MessageFormat.format("PELICULA_ID_NOT_FOUND", id));
		peliculaRepository.deleteById(id);
		
	}

	@Override
	public void update(long id, PeliculaDTO peliculaDTO) throws ParseException, NoSuchElementException{
		if (!peliculaRepository.existsById(id)) throw new NoSuchElementException(MessageFormat.format("PELICULA_ID_NOT_FOUND", id));
		else {
			Set<Personaje> personajes = new HashSet<>();
			// verifica si los personajes asociados existen, sino los crea
			for (String nombre : peliculaDTO.getPersonajes()) {
				if (personajeRepository.existsByNombre(nombre)) {
					personajes.add(personajeRepository.findByNombre(nombre).get());
				} else {
					// crear entidad
					Personaje personaje = new Personaje(nombre);
					// salvar entidad
					personajeRepository.save(personaje);
					// agregar a la lista de peliculas relacionadas
					personajes.add(personaje);
				}
			}
			// verifica si los generos asociados existen, sino los crea
			Set<Genero> generos = new HashSet<>();
			for (String nombre : peliculaDTO.getGeneros()) {
				if (generoRepository.existsByNombre(nombre)) {
					// agrega la entidad a la lista de personajes relacionados
					generos.add(generoRepository.findByNombre(nombre));
				} else {
					// crear entidad
					Genero genero = new Genero(nombre);
					// salvar entidad
					generoRepository.save(genero);
					// agregar a la lista de peliculas relacionadas
					generos.add(genero);
				}
			}
			// mapear el dto a entidad
			Pelicula pelicula = PeliculaDTO.DtoToEntity(id, peliculaDTO, personajes, generos);
			peliculaRepository.save(pelicula);
	}
}

	@Override
	public List<PeliculaSimpleDTO> findByOrderByTitulo(String order) {
		List<PeliculaSimple> peliculas;
		if(order.equals("ASC")) {
			peliculas = peliculaRepository.findByOrderByTituloAsc().orElseThrow(() 
					-> new NoSuchElementException(MessageFormat.format("PELICULA_TITLE_NOT_FOUND", order)));
		}
		else {
			peliculas = peliculaRepository.findByOrderByTituloDesc().orElseThrow(() 
					-> new NoSuchElementException(MessageFormat.format("PELICULA_TITLE_NOT_FOUND", order)));
		}
		List<PeliculaSimpleDTO> peliculaSimpleDTOs = new ArrayList<>(); 
		for(PeliculaSimple peliculaSimple: peliculas) {
			PeliculaSimpleDTO peliculaSimpleDTO = PeliculaSimpleDTO.EntityToDTO(peliculaSimple);
			peliculaSimpleDTOs.add(peliculaSimpleDTO);
		}
		return peliculaSimpleDTOs;
	}

	
}

