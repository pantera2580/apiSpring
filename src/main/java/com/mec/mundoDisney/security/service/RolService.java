package com.mec.mundoDisney.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.mundoDisney.security.enumerados.RoleName;
import com.mec.mundoDisney.security.model.Rol;
import com.mec.mundoDisney.security.repository.RolDao;

@Service
@Transactional
public class RolService {
	
	@Autowired
	private RolDao rolDao;
	
	public Optional<Rol> getByRol(RoleName roleName) {
		return rolDao.findByRoleName(roleName);
	}
	public void save(Rol rol) {
		rolDao.save(rol);
	}
}
