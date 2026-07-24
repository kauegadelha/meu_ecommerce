package com.kauedev.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserInsertDTO {
	@NotBlank(message = "Nome de usuário é obrigatório!")
	@Size(min = 3, max = 60, message = "Nome de usuário deve conter entre 3 a 60 caracteres")
	private String userName;
	
	@NotBlank(message = "Senha de usuário é obrigatória!")
	@Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
	private String password;
	
	@NotBlank(message = "Telefone é obrigatório!")
	private String phone;
	
	@NotNull(message = "Endereço é obrigatório!")
	@Valid
	private AddressInsertDTO address;
	
	public UserInsertDTO() {}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressInsertDTO getAddress() {
		return address;
	}

	public void setAddress(AddressInsertDTO address) {
		this.address = address;
	}
	
	
}
