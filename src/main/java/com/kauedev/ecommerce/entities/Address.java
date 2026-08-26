package com.kauedev.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String state;
	private String city;
	private String neighborhood;
	private String road;
	private String houseNumber;
	
	protected Address() {
		
	}
	
	public Address(String state, String city, String neighborhood, String road, String houseNumber) {
		if (state == null || state.isBlank()) throw new IllegalArgumentException("Estado é obrigatório!");
		this.state = state;
		if (city == null || city.isBlank()) throw new IllegalArgumentException("Cidade é obrigatória!");
		this.city = city;
		if (neighborhood == null || neighborhood.isBlank()) throw new IllegalArgumentException("Bairro é obrigatório!");
		this.neighborhood = neighborhood;
		if (road == null || road.isBlank()) throw new IllegalArgumentException("Rua é obrigatória!");
		this.road = road;
		if (houseNumber == null || houseNumber.isBlank()) throw new IllegalArgumentException("Número da casa/apt é obrigatório!");
		this.houseNumber = houseNumber;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if (state == null || state.isBlank()) throw new IllegalArgumentException("Estado é obrigatório!");
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if (city == null || city.isBlank()) throw new IllegalArgumentException("Cidade é obrigatória!");
		this.city = city;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		if (neighborhood == null || neighborhood.isBlank()) throw new IllegalArgumentException("Bairro é obrigatório!");
		this.neighborhood = neighborhood;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		if (road == null || road.isBlank()) throw new IllegalArgumentException("Rua é obrigatória!");
		this.road = road;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		if (houseNumber == null || houseNumber.isBlank()) throw new IllegalArgumentException("Número da casa/apt é obrigatório!");
		this.houseNumber = houseNumber;
	}
	
	@Override
	public String toString() {
		return """
				Address{
				State:'%s',
				City:'%s', 
				Neighborhood:'%s', 
				Road:'%s', 
				HouseNumber:'%s'}
				""".formatted(state, city, neighborhood, road, houseNumber);
	}
}
