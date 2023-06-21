package com.skillstorm.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.skillstorm.demo.dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotNull(message = "enter a user name")
    private String name;

    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email; 		//This doubles as the user name.

    private String password;
 
    
    public UserDto toDto() {
    	return new UserDto(id, name, email);
    }
	
}
