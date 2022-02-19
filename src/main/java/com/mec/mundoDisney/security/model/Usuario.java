package com.mec.mundoDisney.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	
	
	public Usuario() {
	}
	
	public Usuario(long idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}
	
	public Usuario(@NotEmpty(message = "password no puede estar vacio") String name,
			@NotEmpty(message = "password no puede estar vacio") String userName,
			@NotEmpty(message = "password no puede estar vacio") String email,
			@NotEmpty(message = "password no puede estar vacio") String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Usuario(long idUsuario, @NotEmpty(message = "password no puede estar vacio") String name,
			@NotEmpty(message = "password no puede estar vacio") String userName,
			@NotEmpty(message = "password no puede estar vacio") String email,
			@NotEmpty(message = "password no puede estar vacio") String password, 
			@NotNull Set<Rol> roles) {
		super();
		this.idUsuario = idUsuario;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUsuario;
	
	@NotEmpty(message = "password no puede estar vacio")
	private String name;
	
	@NotEmpty(message = "password no puede estar vacio")
	private String userName;
	
	@NotEmpty(message = "password no puede estar vacio")
	private String email;
	
	@NotEmpty(message = "password no puede estar vacio")
	private String password;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	name = "usuario_rol",
	joinColumns = @JoinColumn(name = "id_usuario"),
	inverseJoinColumns = @JoinColumn(name = "id_rol")
	)
	private Set<Rol> roles = new HashSet<>();

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
}
