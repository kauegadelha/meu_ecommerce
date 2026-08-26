package com.kauedev.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordUpdateDTO {

	@NotBlank(message = "Nova senha é obrigatória!")
	@Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres!")
	private String newPassword;

	public PasswordUpdateDTO() {}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}