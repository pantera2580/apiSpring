package com.mec.mundoDisney.service;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

import com.mec.mundoDisney.dto.PeliculaDTO;
import com.mec.mundoDisney.dto.PeliculaSimpleDTO;
import com.mec.mundoDisney.exception.PeliculaAlreadyExistException;
import com.mec.mundoDisney.model.Pelicula;

public interface IPeliculaService {
		
		//existe por titulo
		public boolean existsByTitulo(String titulo);
		
		//buscar una pelicula
		public PeliculaDTO findByTitulo(String titulo) throws NoSuchElementException;
		
		//salvar una pelicula
		public void save(PeliculaDTO peliculaDTO) throws PeliculaAlreadyExistException, ParseException;
		
		public void update(long id, PeliculaDTO peliculaDTO) throws NoSuchElementException, ParseException;
		
		//buscar por id
		public Pelicula findById(long id) throws NoSuchElementException;
		
		//buscar todo listando imagen, titulo y fecha
		public List<PeliculaSimpleDTO> findAllBy();
		
		//verifica si existe un id
		public boolean existsById(long id);

		public void delete(long id) throws NoSuchElementException;
		
		public List<PeliculaSimpleDTO> findByOrderByTitulo(String order) throws NoSuchElementException;
		
		
}
