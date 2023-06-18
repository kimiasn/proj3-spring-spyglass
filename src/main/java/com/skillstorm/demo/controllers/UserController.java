package com.skillstorm.demo.controllers;

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

import com.skillstorm.demo.dtos.UserDto;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@GetMapping("/{id}") 
	public UserDto findUserById(@PathVariable long id){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userData) {
		return null;
	}
	
	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userData) {
		userData.setId(id);
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteStore(@PathVariable long id) {
		
	}
}
