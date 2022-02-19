package com.mec.mundoDisney.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mec.mundoDisney.security.enumerados.RoleName;
import com.mec.mundoDisney.security.model.Rol;

@Repository
public interface RolDao extends JpaRepository<Rol, Long>{
	
	Optional<Rol> findByRoleName(RoleName roleName);
	
}
