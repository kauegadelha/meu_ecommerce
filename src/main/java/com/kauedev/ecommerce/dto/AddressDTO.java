package com.kauedev.ecommerce.dto;

import com.kauedev.ecommerce.entities.Address;

public class AddressDTO {
	private Long id;
	private String state;
	private String city;
	private String neighborhood;
	private String road;
	private String houseNumber;
	
	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.state = entity.getState();
		this.city = entity.getCity();	
		this.neighborhood = entity.getNeighborhood();
		this.road = entity.getRoad();
		this.houseNumber = entity.getHouseNumber();
	}

	public Long getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getRoad() {
		return road;
	}

	public String getHouseNumber() {
		return houseNumber;
	}
}
