package com.mec.mundoDisney.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
	@NotBlank
	private String userName;

	@NotBlank
	private String password;
	
	public LoginUsuario() {
		super();
	}

	public LoginUsuario(@NotBlank String userName, @NotBlank String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
