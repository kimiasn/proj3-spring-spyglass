package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.dtos.UserDto;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDto>> findAllUsers(){
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}
	
	// ?? how to protect this so users can not access
	/**
	 * 
	 * @param id The id of the user
	 * @return The data of the user
	 */
	@GetMapping("/{id}") 
	public ResponseEntity<UserDto> findUserById(@PathVariable long id){
		UserDto userDto = userService.findUserById(id);
		if (userDto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserDto> createUser(@RequestBody User userData) {
		try {
		UserDto userDto = userService.findUserById(userData.getId());
			if (userDto != null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e){
			System.out.println(e);
			
		}
		UserDto user = userService.createUser(userData);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody User userData) {
		UserDto userDto = userService.findUserById(userData.getId());
		if (userDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		UserDto user = userService.updateUser(userData);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		try {
			UserDto userDto = userService.findUserById(id);
			
			if (userDto == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}

