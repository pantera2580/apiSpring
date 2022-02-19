package com.mec.mundoDisney.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mec.mundoDisney.security.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
			
	Boolean existsByEmail(String email);
}
