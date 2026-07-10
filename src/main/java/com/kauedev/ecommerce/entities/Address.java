package com.kauedev.ecommerce.entities;

public class Address {
	private String state;
	private String city;
	private String neighborhood;
	private String road;
	private String houseNumber;
	
	public Address(String state, String city, String neighborhood, String road, String houseNumber) {
		this.state = state;
		this.city = city;
		this.neighborhood = neighborhood;
		this.road = road;
		this.houseNumber = houseNumber;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
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
