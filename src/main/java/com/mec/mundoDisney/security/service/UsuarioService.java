package com.mec.mundoDisney.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.mundoDisney.security.model.Usuario;
import com.mec.mundoDisney.security.repository.UsuarioDao;

@Service
@Transactional 
public class UsuarioService {
	@Autowired
	private UsuarioDao usuarioDao;
	
	public Optional<Usuario> getByUserName(String userName) {
		return usuarioDao.findByUserName(userName);
	}
	
	public boolean existsByUserName(String userName) {
		return usuarioDao.existsByUserName(userName);
	}
	
	public boolean existByEmail(String email) {
		return usuarioDao.existsByEmail(email);
	}
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}
}
