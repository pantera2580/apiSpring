package com.mec.mundoDisney.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mec.mundoDisney.dto.GeneroDTO;
import com.mec.mundoDisney.model.Genero;
import com.mec.mundoDisney.repository.GeneroRepository;

@Service
public class GeneroService implements IGeneroService{

	@Autowired
	GeneroRepository generoRepository;
	
	//recupera el elemento por nombre
	@Override
	@Transactional(readOnly = true)
	public Genero findByNombre(String nombre) {
		return generoRepository.findByNombre(nombre);
	}
	
	//salva o actualiza una elemento
	@Transactional
	@Override
	public void save(Genero genero) {
		// TODO Auto-generated method stub
		
	}
	
	//elimina un elemento por id
	@Transactional
	@Override
	public void delete(long id) {
		generoRepository.deleteById(id);
	}
	
	//recupera todos los campos de todos los elementos
	@Transactional
	@Override
	public List<Genero> listAll() {
		return generoRepository.findAll();
		
	}
	@Override
	@Transactional(readOnly = true)
	public boolean existsById(long id) {
		return generoRepository.existsById(id);
	}
	
	
	@Override
	public GeneroDTO findById(long id) throws NoSuchElementException{
		Genero genero = generoRepository.findById(id).orElseThrow(() 
				-> new NoSuchElementException(MessageFormat.format("GENRE_NOT_FOUND", id)));
		GeneroDTO generoDTO = GeneroDTO.EntityToDTO(genero);
		return generoDTO;
	}
}
