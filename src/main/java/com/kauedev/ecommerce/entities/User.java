package com.kauedev.ecommerce.entities;

public class User {
	private String userName;
	private String password;
	private String phone;
	private Address address;
	public enum role{
		CLIENT,
		ADMIN,
		MANAGER,
	};
	
	
	public User(String userName, String password, String phone, Address address) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}
	

}
