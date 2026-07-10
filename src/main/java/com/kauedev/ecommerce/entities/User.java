package com.kauedev.ecommerce.entities;

public class User {
	private String userName;
	private String password;
	private String phone;
	private Address address;
	public enum Role{
		CLIENT,
		ADMIN,
		MANAGER,
	};
	private Role role;
	
	
	public User(String userName, String password, String phone, Address address, Role role) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

}
