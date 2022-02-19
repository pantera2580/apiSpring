package com.mec.mundoDisney.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsuario {
	@NotBlank
	private String userName;

	@NotBlank
	private String password;
}
