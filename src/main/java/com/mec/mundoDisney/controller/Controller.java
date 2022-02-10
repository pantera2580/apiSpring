package com.mec.mundoDisney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mec.mundoDisney.model.Personaje;
import com.mec.mundoDisney.service.PersonajeService;

@RestController
public class Controller {
	@Autowired
	PersonajeService personajeService;
	@GetMapping("/index")
	public String saludo(){
		return "index";
	}
	@GetMapping("/characters") 
	public List<Personaje> listarPersonajes() {
		return this.personajeService.listarPersonajes();
	}
}
