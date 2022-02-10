package com.mec.mundoDisney.repository;

import org.springframework.data.repository.CrudRepository;

import com.mec.mundoDisney.model.Pelicula;

public interface PeliculaDao extends CrudRepository<Pelicula, Long>{

}
