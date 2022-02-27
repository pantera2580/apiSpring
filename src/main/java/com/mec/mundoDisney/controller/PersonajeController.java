package com.mec.mundoDisney.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mec.mundoDisney.dto.Mensaje;
import com.mec.mundoDisney.dto.PeliculaDTOTitulo;
import com.mec.mundoDisney.dto.PersonajeDTO;
import com.mec.mundoDisney.dto.custom.PersonajeSimple;
import com.mec.mundoDisney.exception.PersonajeAlreadyExistException;
import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.service.IPeliculaService;
import com.mec.mundoDisney.service.IPersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
	@Autowired
	IPersonajeService personajeService;
	
	@Autowired
	IPeliculaService peliculaService;
	
	// listar todos
	@GetMapping()
	public ResponseEntity<?> listarPersonajes(@RequestParam(required = false) String name,
			@RequestParam(required = false, defaultValue = "0") int age, 
			@RequestParam(required = false, defaultValue = "0") int weigth,
			@RequestParam(required = false, defaultValue = "0")long movies) {
	
		//buscar por nombre
		if(name!=null) {
			PersonajeDTO personajeDTO = personajeService.findByNombre(name);
			return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
		}
		//filtro por edad
		if(age>0) {
			List<PersonajeDTO> personajeDTO = personajeService.findByEdad(age);
			return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
		}
		
		//filtro por peso
		if (weigth > 0) {
			List<PersonajeDTO> personajeDTO = personajeService.findByPeso(weigth);
			return new ResponseEntity<>(personajeDTO, HttpStatus.OK);
		}
		
		// filtro por peliculas
		if (movies > 0) {
			Pelicula pelicula = peliculaService.findById(movies);
			if (pelicula != null) {
				PeliculaDTOTitulo peliculaDTOTitulo = PeliculaDTOTitulo.EntityToDTO(pelicula);
				return new ResponseEntity<>(peliculaDTOTitulo, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		//listar todos pero solo imagen y nombre
		List<PersonajeSimple> listPersonajes = this.personajeService.findAllByPictureAndName();
		return new ResponseEntity<List<PersonajeSimple>>(listPersonajes, HttpStatus.OK);

		
	}
	
	// guardar
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Mensaje> save(@RequestBody @Valid PersonajeDTO personajeDTO) throws PersonajeAlreadyExistException {
		
			personajeService.save(personajeDTO);
			return new ResponseEntity<Mensaje>(new Mensaje("Se ha creado la entrada"), HttpStatus.CREATED);

	}
	
	// actualizar
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Mensaje> update(@PathVariable("id") long id, @RequestBody @Valid PersonajeDTO personajeDTO) {
		
		personajeService.update(id, personajeDTO);
		return new ResponseEntity<Mensaje>(new Mensaje("Se ha actualizado la entrada"), HttpStatus.CREATED);
	}
	
	// eliminar
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Mensaje> delete(@PathVariable("id") long id) {
			personajeService.delete(id);
			return new ResponseEntity<Mensaje>(new Mensaje("Entrada eliminada"), HttpStatus.ACCEPTED);
	}
	
	// detalle de un personaje
	@GetMapping("/{id}")
	public ResponseEntity<PersonajeDTO> show(@PathVariable("id") long id) {
			PersonajeDTO personajeDTO = personajeService.findById(id);
			return new ResponseEntity<PersonajeDTO>(personajeDTO, HttpStatus.OK);

	}

}
