package com.skillstorm.demo.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.skillstorm.demo.dtos.GoalDto;

import lombok.Data;

@Entity
@Data
@Table(name="goal")
public class Goal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "A Title is required")
	private String title;
	private Date startDate;
	private Date targetDate;
	private BigDecimal startAmount;
	private BigDecimal targetAmount;
	private BigDecimal currentAmount;
	private String description;
	private String category;
	private String photoURL;
	
	@JoinColumn(name = "user_id")
	@OneToMany
	private User user;
	
	/**
	 * Method that converts the Goal entity into the Goal DTO
	 * @return The DTO
	 */
	public GoalDto toDto() {
		return new GoalDto(id, title, startDate, targetDate, targetAmount,
				startAmount, currentAmount, description, category, photoURL);
	}
	
}
