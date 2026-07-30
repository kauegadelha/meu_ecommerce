package com.kauedev.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
	
	@NotBlank(message = "Nome de usuário é obrigatório!")
	private String userName;
	
	@NotBlank(message = "Senha é obrigatória!")
	private String password;
	
	public LoginRequestDTO() {
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
