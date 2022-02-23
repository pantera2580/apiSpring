package com.mec.mundoDisney.security.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.mec.mundoDisney.security.enumerados.RoleName;

@Entity
@Table(name = "rol")
public class Rol{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRol;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Usuario> usuarios;
	
	
	public Rol() {
		super();
	}	

	public Rol(@NotNull RoleName roleName) {
		super();
		this.roleName = roleName;
	}
	
	
	public Rol(long idRol, @NotNull RoleName roleName, Set<Usuario> usuarios) {
		super();
		this.idRol = idRol;
		this.roleName = roleName;
		this.usuarios = usuarios;
	}

	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
