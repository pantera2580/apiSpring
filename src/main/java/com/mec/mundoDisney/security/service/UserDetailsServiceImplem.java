package com.mec.mundoDisney.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mec.mundoDisney.security.model.Usuario;
import com.mec.mundoDisney.security.model.UsuarioPrincipal;

@Service
public class UserDetailsServiceImplem implements UserDetailsService{
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioService.getByUserName(username).get(); //no olvidar que es un optional
		return UsuarioPrincipal.build(usuario);
	}

}
