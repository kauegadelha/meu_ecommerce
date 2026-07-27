package com.kauedev.ecommerce.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
	
	@NotBlank(message = "Nome de usuário é obrigatório!")
	@Size(min = 3, max = 60, message = "Nome de usuário deve conter entre 3 a 60 caracteres")
	private String userName;
	
	@NotBlank(message = "Telefone é obrigatório!")
	private String phone;
	
	@NotNull(message = "Endereço é obrigatório!")
	@Valid
	private AddressInsertDTO address;
	
	public UserUpdateDTO() {}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
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
