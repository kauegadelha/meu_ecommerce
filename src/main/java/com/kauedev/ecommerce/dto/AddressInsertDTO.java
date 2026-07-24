package com.kauedev.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressInsertDTO {
	@NotBlank(message = "Estado é obrigatório")
    private String state;
	
	@NotBlank(message = "Cidade é obrigatória")
    private String city;
	
	@NotBlank(message = "Bairro é obrigatório")
    private String neighborhood;
	
	@NotBlank(message = "Rua é obrigatória")
    private String road;
	
	@NotBlank(message = "Número da casa/apt é obrigatório!")
    private String houseNumber;

    public AddressInsertDTO() {}

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
}
