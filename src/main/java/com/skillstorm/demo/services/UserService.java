package com.skillstorm.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.dtos.UserDto;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired 
	private UserRepository userRepository;
	
	public List<UserDto> findAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(u -> u.toDto())
				.toList();
	}
	
	public UserDto findUserById(long id) {
//		System.out.println("find user by id: " + id);
		return userRepository.findById(id)
				.orElseThrow()  
				.toDto();
	}
	
	/**
	 * 
	 * @param userData The data to create a user entity with
	 * @return The data for the newly created user
	 */
	public UserDto createUser(User userData) {
		// Question ??
		// should there be a check to see if the user exists already
		// or should i let it happen down stream?
		User user = User.builder()
				.name(userData.getName())
				.email(userData.getEmail())
				.password(userData.getPassword())
				.build();
		
		return userRepository.save(user).toDto();
	}
	
	/**
	 * 
	 * @param userData The data to create a user entity with
	 * @return The data for the newly created user
	 */
	public UserDto updateUser(User userData) {
		User user = new User(0, userData.getName(), 
				userData.getEmail(), userData.getPassword());
		return userRepository.save(user).toDto();
	}
	
	/**
	 * 
	 * @param id the user identifier
	 */
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
}
