package com.mec.mundoDisney.controller;

import java.text.ParseException;
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

import com.mec.mundoDisney.dto.GeneroDTO;
import com.mec.mundoDisney.dto.Mensaje;
import com.mec.mundoDisney.dto.PeliculaDTO;
import com.mec.mundoDisney.dto.PeliculaSimpleDTO;
import com.mec.mundoDisney.exception.PeliculaAlreadyExistException;
import com.mec.mundoDisney.model.Pelicula;
import com.mec.mundoDisney.service.IGeneroService;
import com.mec.mundoDisney.service.IPeliculaService;

@RestController
@RequestMapping("/movies")
public class PeliculasController {

	@Autowired
	IPeliculaService peliculaService;

	@Autowired
	IGeneroService generoService;

	@GetMapping
	public ResponseEntity<?> listEntity(@RequestParam(required = false) String name,
			@RequestParam(required = false, defaultValue = "0") int genre,
			@RequestParam(required = false, defaultValue = "ASC") String sort) {
		if(name!=null) {
			PeliculaDTO peliculaDTO = peliculaService.findByTitulo(name);
			return new ResponseEntity<>(peliculaDTO, HttpStatus.OK);
		}
		if(genre>0) {
			GeneroDTO generoDTO = generoService.findById(genre);
			return new ResponseEntity<>(generoDTO, HttpStatus.OK);
		}
		if(sort.equals("ASC") || sort.equals("DESC")) {
			List<PeliculaSimpleDTO> peliculasSimpleDTO = peliculaService.findByOrderByTitulo(sort);
			return new ResponseEntity<>(peliculasSimpleDTO, HttpStatus.OK);
		}
		List<PeliculaSimpleDTO> peliculasDTO = peliculaService.findAllBy();
		return new ResponseEntity<List<PeliculaSimpleDTO>>(peliculasDTO, HttpStatus.OK);
	}

	// guardar
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Mensaje> save(@RequestBody @Valid PeliculaDTO peliculaDTO)
			throws ParseException, PeliculaAlreadyExistException {
		peliculaService.save(peliculaDTO);
		return new ResponseEntity<Mensaje>(new Mensaje("Se ha creado la entrada"), HttpStatus.CREATED);

	}

	// actualizar
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Mensaje> update(@PathVariable("id") long id, @RequestBody @Valid PeliculaDTO peliculaDTO)
			throws ParseException {

		// salva la solicitud
		peliculaService.update(id, peliculaDTO);
		return new ResponseEntity<Mensaje>(new Mensaje("Se ha actualizado la entrada"), HttpStatus.CREATED);

	}

	// eliminar
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Mensaje> delete(@PathVariable("id") long id) {
		// if (peliculaService.existsById(id)) {
		peliculaService.delete(id);
		return new ResponseEntity<Mensaje>(new Mensaje("Entrada eliminada"), HttpStatus.ACCEPTED);

	}

	// detalle de un personaje
	@GetMapping("/{id}")
	public ResponseEntity<PeliculaDTO> show(@PathVariable("id") long id) {
		Pelicula pelicula = peliculaService.findById(id);
		PeliculaDTO peliculaDTO = PeliculaDTO.EntityToDto(pelicula);
		return new ResponseEntity<PeliculaDTO>(peliculaDTO, HttpStatus.OK);
	}
	
}
