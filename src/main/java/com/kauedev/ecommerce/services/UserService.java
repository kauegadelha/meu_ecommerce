package com.kauedev.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kauedev.ecommerce.dto.AddressInsertDTO;
import com.kauedev.ecommerce.dto.UserDTO;
import com.kauedev.ecommerce.dto.UserInsertDTO;
import com.kauedev.ecommerce.entities.Address;
import com.kauedev.ecommerce.entities.User;
import com.kauedev.ecommerce.entities.User.Role;
import com.kauedev.ecommerce.repositories.UserRepository;
import com.kauedev.ecommerce.services.exceptions.ResourceNotFoundException;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private void validateUserExists(Long id) {
	    if (!userRepository.existsById(id)) throw new ResourceNotFoundException("Usuário não encontrado, id: %d".formatted(id));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findUser(String name) {
		User result = userRepository.findByName(name)
		.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: %s".formatted(name)));
		return new UserDTO(result);
	}
	
	@Transactional
	public UserDTO insertUser(UserInsertDTO dto) {
		AddressInsertDTO addressDTO = dto.getAddress();
		
		Address address = new Address(
			addressDTO.getState(),
			addressDTO.getCity(),
			addressDTO.getNeighborhood(),
			addressDTO.getRoad(),
			addressDTO.getHouseNumber()
			);
		
		User user = new User(
			dto.getUserName(),
			dto.getPassword(),
			dto.getPhone(),
			address,
			Role.CLIENT
				);
		
		user = userRepository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO updateUser (Long id, UserInsertDTO dto) {
		validateUserExists(id);
		
		User user = userRepository.getReferenceById(id);
		
		user.setUserName(dto.getUserName());
		user.setPhone(dto.getPhone());
		
		AddressInsertDTO addressDTO = dto.getAddress();
		Address address = user.getAddress();
		
		address.setState(addressDTO.getState());
		address.setCity(addressDTO.getCity());
		address.setNeighborhood(addressDTO.getNeighborhood());	
		address.setRoad(addressDTO.getRoad());
		address.setHouseNumber(addressDTO.getHouseNumber());
		
		
		user = userRepository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public void updatePassword(Long id, String newPassword) {
		validateUserExists(id);
		
	    User user = userRepository.getReferenceById(id);
	    user.setPassword(newPassword);
	    userRepository.save(user);
	}
	
	@Transactional
	public void deleteUser(Long id) {
		validateUserExists(id);
		
		userRepository.deleteById(id);
	}
}
