package com.mec.mundoDisney.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mec.mundoDisney.dto.PersonajeDTO;
import com.mec.mundoDisney.dto.custom.PersonajeSimple;
import com.mec.mundoDisney.exception.PersonajeAlreadyExistException;
import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.repository.PeliculaRepository;
import com.mec.mundoDisney.repository.PersonajeRepository;

@Service
public class PersonajeService implements IPersonajeService {
	@Autowired()
	PersonajeRepository personajeRepository;

	@Autowired
	PeliculaRepository peliculaRepository;

	// guardar
	@Transactional
	@Override
	public void save(PersonajeDTO personajeDTO) throws PersonajeAlreadyExistException {
		if (personajeRepository.existsByNombre(personajeDTO.getNombre()))
			throw new PersonajeAlreadyExistException(personajeDTO.getNombre());
		else {
			Set<Pelicula> peliculas = new HashSet<>();
			Pelicula pelicula;
			for (String titulo : personajeDTO.getPeliculas()) {
				if (peliculaRepository.existsByTitulo(titulo)) {
					// si existe recupero la entidad
					pelicula = peliculaRepository.findByTitulo(titulo).get();
				} else {
					// sino crear la entidad
					pelicula = new Pelicula(titulo);
					// salvar entidad
					peliculaRepository.save(pelicula);
				}
				// agregar a la lista de peliculas relacionadas
				peliculas.add(pelicula);
			}
			// mapear el dto a entidad
			Personaje personaje = PersonajeDTO.DtoToEntity(personajeDTO, peliculas);
			// salva la solicitud
			personajeRepository.save(personaje);
		}
	}

	@Transactional
	@Override
	public void update(long id, PersonajeDTO personajeDTO) throws NoSuchElementException {
		if (!personajeRepository.existsById(id)) throw new NoSuchElementException(MessageFormat.format("PERSONAJE_ID_NOT_FOUND", id));
		else {
			Set<Pelicula> peliculas = new HashSet<>();
			for (String titulo : personajeDTO.getPeliculas()) {
				Pelicula pelicula;
				if (peliculaRepository.existsByTitulo(titulo)) {
					// si existe recupero la entidad
					pelicula = peliculaRepository.findByTitulo(titulo).get();
				} else {
					// sino, crear entidad
					pelicula = new Pelicula(titulo);
					// salvar entidad
					peliculaRepository.save(pelicula);
				}
				// agregar a la lista de peliculas relacionadas
				peliculas.add(pelicula);
			}
			Personaje personaje = PersonajeDTO.DtoToEntity(id, personajeDTO, peliculas);
			personajeRepository.save(personaje);

		}
	}

	// listar todos los campos
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findAll() {
		return (ArrayList<Personaje>) this.personajeRepository.findAll();
	}

	// listar solo imagen y nombre
	@Transactional(readOnly = true)
	@Override
	public List<PersonajeSimple> findAllByPictureAndName() throws NoSuchElementException {
		return personajeRepository.findAllByPictureAndName().orElseThrow(() 
				-> new NoSuchElementException(MessageFormat.format("PERSONAJE_TITLE_NOT_FOUND", "null")));
	}

	// existe por id
	@Transactional(readOnly = true)
	@Override
	public boolean existsById(long idPersonaje) {
		return personajeRepository.existsById(idPersonaje);

	}

	// existe por nombre
	@Transactional(readOnly = true)
	@Override
	public boolean existsByNombre(String nombre) {
		return personajeRepository.existsByNombre(nombre);
	}

	// borrar
	@Transactional
	@Override
	public void delete(long idPersonaje) throws NoSuchElementException{
		if(!personajeRepository.existsById(idPersonaje)) throw new NoSuchElementException(MessageFormat.format("PERSONAJE_ID_NOT_FOUND", idPersonaje));
		personajeRepository.deleteById(idPersonaje);
	}

	// busqueda por id
	@Transactional(readOnly = true)
	@Override
	public PersonajeDTO findById(long id) throws NoSuchElementException{
		if(!personajeRepository.existsById(id)) throw new NoSuchElementException(MessageFormat.format("PERSONAJE_ID_NOT_FOUND", id));
		PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personajeRepository.findById(id));
		return personajeDTO;
	}

	// busqueda por edad
	@Transactional(readOnly = true)
	public List<PersonajeDTO> findByEdad(int edad) throws NoSuchElementException{
		List<Personaje> personajes = personajeRepository.findByEdad(edad)
				.orElseThrow(() -> new NoSuchElementException(MessageFormat.format("PERSONAJE_AGE_NOT_FOUND", edad)));
		List<PersonajeDTO> listaPersonajeDto = new ArrayList<>();
		for (Personaje personaje : personajes) {
			PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personaje);
			listaPersonajeDto.add(personajeDTO);
			}
		return listaPersonajeDto;
	};

	// busqueda por peso
	@Transactional(readOnly = true)
	public List<PersonajeDTO> findByPeso(int peso) throws NoSuchElementException {
		List<Personaje> personajes = personajeRepository.findByPeso(peso)
				.orElseThrow(() -> new NoSuchElementException(MessageFormat.format("PERSONAJE_WEIGTH_NOT_FOUND", peso)));
		List<PersonajeDTO> listaPersonajeDto = new ArrayList<>();
		for (Personaje personaje : personajes) {
			PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personaje);
			listaPersonajeDto.add(personajeDTO);
			}
		return listaPersonajeDto;
	};
	
	// busqueda por nombre
		@Transactional(readOnly = true)
	public PersonajeDTO findByNombre(String nombre) throws NoSuchElementException{
		Personaje personaje = personajeRepository.findByNombre(nombre)
				.orElseThrow(() -> new NoSuchElementException(MessageFormat.format("PERSONAJE_NAME_NOT_FOUND", nombre)));
		return PersonajeDTO.EntityToDto(personaje);
	}
}
