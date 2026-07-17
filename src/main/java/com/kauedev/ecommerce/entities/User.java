package com.kauedev.ecommerce.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String userName;
	
	private String password;
	private String phone;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="address_id", nullable = false, unique = true)
	private Address address;
	
	public enum Role{
		CLIENT,
		ADMIN,
		MANAGER,
	};
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	protected User() {
		
	}
	
	public Role getRole() {
		return role;
	}

	public User(String userName, String password, String phone, Address address, Role role) {
		if (userName.isEmpty() || userName.isBlank()) throw new IllegalArgumentException("Nome de usuário é obrigatório!");
		this.userName = userName;
		if (password.isEmpty() || password.isBlank()) throw new IllegalArgumentException("Senha de usuário é obrigatório!");
		this.password = password;
		if (phone.isEmpty() || phone.isBlank()) throw new IllegalArgumentException("Número de telefone é obrigatório!");
		this.phone = phone;
		if (address == null) throw new IllegalArgumentException("Endereço é obrigatório!");
		this.address = address;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		if (userName.isEmpty() || userName.isBlank()) throw new IllegalArgumentException("Nome de usuário é obrigatório!");
		this.userName = userName;
	}

	public void setPassword(String password) {
		if (password.isEmpty() || password.isBlank()) throw new IllegalArgumentException("Senha de usuário é obrigatório!");
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		if (phone.isEmpty() || phone.isBlank()) throw new IllegalArgumentException("Número de telefone é obrigatório!");
		this.phone = phone;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		if (address == null) throw new IllegalArgumentException("Endereço é obrigatório!");
		this.address = address;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof User)) return false;

		User other = (User) obj;
		return userName.equals(other.userName);
	}

	@Override
	public int hashCode() {
		return userName.hashCode();
	}

}
