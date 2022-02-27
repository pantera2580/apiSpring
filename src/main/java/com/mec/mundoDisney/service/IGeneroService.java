package com.mec.mundoDisney.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.mec.mundoDisney.dto.GeneroDTO;
import com.mec.mundoDisney.model.Genero;

public interface IGeneroService {
	
	public void save(Genero genero);
	
	public void delete(long id);
	
	public List<Genero> listAll();
	
	public Genero findByNombre(String nombre);
	
	public boolean existsById(long id);
	
	public GeneroDTO findById(long id) throws NoSuchElementException;
}
