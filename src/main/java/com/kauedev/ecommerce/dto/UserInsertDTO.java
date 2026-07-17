package com.kauedev.ecommerce.dto;


public class UserInsertDTO {
	private String userName;
	private String password;
	private String phone;
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
