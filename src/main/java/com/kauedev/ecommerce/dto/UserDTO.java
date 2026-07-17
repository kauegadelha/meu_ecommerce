package com.kauedev.ecommerce.dto;

import com.kauedev.ecommerce.entities.User;
import com.kauedev.ecommerce.entities.User.Role;

public class UserDTO {
    private Long id;
    private String userName;
    private String phone;
    private AddressDTO address;
    private Role role;

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.phone = entity.getPhone();
        this.address = new AddressDTO(entity.getAddress());
        this.role = entity.getRole();
    }

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPhone() {
		return phone;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public Role getRole() {
		return role;
	}
	
}
