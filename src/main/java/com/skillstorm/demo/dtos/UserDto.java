package com.skillstorm.demo.dtos;

import lombok.Data;

@Data
public class UserDto {

	private long id;
	private String name;
	private String email;
	
	public UserDto() {
		
	}
	
	public UserDto(long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
}
