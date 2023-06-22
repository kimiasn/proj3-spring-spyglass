package com.skillstorm.demo.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.skillstorm.demo.dtos.GoalDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="goal")
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String userId;
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
	
//	@JoinColumn(name = "user_id")
//	@ManyToOne
//	private User user;
	
	
	/**
	 * Method that converts the Goal entity into the Goal DTO
	 * @return The DTO
	 */
	public GoalDto toDto() {
		return new GoalDto(id, userId, title, startDate, targetDate, startAmount,
				targetAmount, currentAmount, description, category, photoURL);
	}
	
}
