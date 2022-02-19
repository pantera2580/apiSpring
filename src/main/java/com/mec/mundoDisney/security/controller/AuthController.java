package com.mec.mundoDisney.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mec.mundoDisney.dto.Mensaje;
import com.mec.mundoDisney.security.dto.JwtDto;
import com.mec.mundoDisney.security.dto.LoginUsuario;
import com.mec.mundoDisney.security.dto.NuevoUsuario;
import com.mec.mundoDisney.security.enumerados.RoleName;
import com.mec.mundoDisney.security.jwt.JwtProvider;
import com.mec.mundoDisney.security.model.Rol;
import com.mec.mundoDisney.security.model.Usuario;
import com.mec.mundoDisney.security.service.RolService;
import com.mec.mundoDisney.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/register")
	public ResponseEntity<Mensaje> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existsByUserName(nuevoUsuario.getUserName())) {
			return new ResponseEntity<Mensaje>(new Mensaje("Usuario ya registrado"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity<Mensaje>(new Mensaje("Email ya registrado"), HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = 
				new Usuario(nuevoUsuario.getName(), nuevoUsuario.getUserName(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRol(RoleName.ROLE_USER).get()); //todos los nuevos usuarios van a tener el rol de user por defecto
		if(!nuevoUsuario.getRoles().isEmpty()) {
			if(nuevoUsuario.getRoles().contains("admin")) {
				roles.add(rolService.getByRol(RoleName.ROLE_ADMIN).get()); //asigna rol de admin
			}
		}
		usuario.setRoles(roles); //setear roles 
		usuarioService.save(usuario); //guardar usuario
		return new ResponseEntity<Mensaje>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
}
