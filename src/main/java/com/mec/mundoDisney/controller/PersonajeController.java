package com.mec.mundoDisney.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mec.mundoDisney.dto.Mensaje;
import com.mec.mundoDisney.dto.PeliculaDTOTitulo;
import com.mec.mundoDisney.dto.PersonajeDTO;
import com.mec.mundoDisney.dto.PersonajeDTOSimple;
import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.service.PeliculaService;
import com.mec.mundoDisney.service.PersonajeService;

@RestController
public class PersonajeController {
	@Autowired
	PersonajeService personajeService;
	
	@Autowired
	PeliculaService peliculaService;
	
	// listar todos
	@GetMapping("/characters")
	public ResponseEntity<?> listarPersonajes(@RequestParam(required = false) String name,
			@RequestParam(required = false, defaultValue = "0") int age, 
			@RequestParam(required = false, defaultValue = "0") int weigth,
			@RequestParam(required = false, defaultValue = "0")long movies) {
		//listar todos pero solo imagen y nombre
		if(name==null && age==0 && weigth==0 && movies==0) {
			List<PersonajeDTOSimple> listPersonajes = this.personajeService.findAllByPictureAndName();
			if (listPersonajes != null) {
				return new ResponseEntity<List<PersonajeDTOSimple>>(listPersonajes, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		//buscar por nombre
		if(name!=null && age==0 && weigth==0 && movies==0) {
			Personaje personaje = personajeService.findByNombre(name);
			if (personaje != null) {
				PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personaje);
				return new ResponseEntity<PersonajeDTO>(personajeDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		//filtro por edad
		if(name==null && age>0 && weigth==0 && movies==0) {
			List<Personaje> personajes = personajeService.findByEdad(age);
			if (personajes != null) {
				List<PersonajeDTO> listaPersonajeDto = new ArrayList<>();
				for (Personaje personaje : personajes) {
					PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personaje);
					listaPersonajeDto.add(personajeDTO);
				}
				return new ResponseEntity<List<PersonajeDTO>>(listaPersonajeDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		//filtro por peso
		if (name == null && age == 0 && weigth > 0 && movies==0) {
			List<Personaje> personajes = personajeService.findByPeso(weigth);
			if (personajes != null) {
				List<PersonajeDTO> listaPersonajeDto = new ArrayList<>();
				for (Personaje personaje : personajes) {
					PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personaje);
					listaPersonajeDto.add(personajeDTO);
				}
				return new ResponseEntity<List<PersonajeDTO>>(listaPersonajeDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		// filtro por peliculas
		if (name == null && age == 0 && weigth == 0 && movies > 0) {
			Pelicula pelicula = peliculaService.findById(movies);
			if (pelicula != null) {
				PeliculaDTOTitulo peliculaDTOTitulo = PeliculaDTOTitulo.EntityToDTO(pelicula);
				return new ResponseEntity<>(peliculaDTOTitulo, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	// guardar
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/characters")
	public ResponseEntity<Mensaje> save(@RequestBody PersonajeDTO personajeDTO) {
		if (!personajeService.existsByNombre(personajeDTO.getNombre())) {
			Set<Pelicula> peliculas = new HashSet<>();
			for (String titulo : personajeDTO.getPeliculas()) {
				Pelicula pelicula = peliculaService.findByTitulo(titulo);
				if (pelicula != null) {
					peliculas.add(pelicula);
				} else {
					// crear entidad
					pelicula = new Pelicula(titulo);
					// salvar entidad
					peliculaService.save(pelicula);
					// agregar a la lista de peliculas relacionadas
					peliculas.add(pelicula);
				}
			}
			// mapear el dto a entidad
			Personaje personaje = PersonajeDTO.DtoToEntity(personajeDTO, peliculas);
			// salva la solicitud
			personajeService.save(personaje);
			return new ResponseEntity<Mensaje>(new Mensaje("Se ha creado la entrada"), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Mensaje>(new Mensaje("La entrada ya existe"), HttpStatus.BAD_REQUEST);
		}

	}
	
	// actualizar
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/characters/{id}")
	public ResponseEntity<Mensaje> update(@PathVariable("id") long id, @RequestBody PersonajeDTO personajeDTO) {
		if (personajeService.existsById(id)) {
			Set<Pelicula> peliculas = new HashSet<>();
			for (String titulo : personajeDTO.getPeliculas()) {
				Pelicula pelicula = peliculaService.findByTitulo(titulo);
				if (pelicula != null) {
					peliculas.add(pelicula);
				} else {
					// crear entidad
					pelicula = new Pelicula(titulo);
					// salvar entidad
					peliculaService.save(pelicula);
					// agregar a la lista de peliculas relacionadas
					peliculas.add(pelicula);
				}
			}
			Personaje personaje = PersonajeDTO.DtoToEntity(id, personajeDTO, peliculas);
			personajeService.save(personaje);
			return new ResponseEntity<Mensaje>(new Mensaje("Se ha actualizado la entrada"), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Mensaje>(new Mensaje("La entrada no existe"), HttpStatus.NOT_FOUND);
		}
	}
	
	// eliminar
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/characters/{id}")
	public ResponseEntity<Mensaje> delete(@PathVariable("id") long id) {
		if (personajeService.existsById(id)) {
			personajeService.delete(id);
			return new ResponseEntity<Mensaje>(new Mensaje("Entrada eliminada"), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Mensaje>(new Mensaje("La entrada no existe"), HttpStatus.NOT_FOUND);
		}
	}
	
	// detalle de un personaje
	@GetMapping("/characters/{id}")
	public ResponseEntity<PersonajeDTO> show(@PathVariable("id") long id) {
		if (personajeService.existsById(id)) {
			Personaje personaje = personajeService.findById(id);
			PersonajeDTO personajeDTO = PersonajeDTO.EntityToDto(personaje);
			return new ResponseEntity<PersonajeDTO>(personajeDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
		
}
