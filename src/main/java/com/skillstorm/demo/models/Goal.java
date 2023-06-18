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
	@ManyToOne
	private User user;
	
	
	
	
	/**
	 * Method that converts the Goal entity into the Goal DTO
	 * @return The DTO
	 */
	public GoalDto toDto() {
		return new GoalDto(id, user.getId(), title, startDate, targetDate, startAmount,
				targetAmount, currentAmount, description, category, photoURL);
	}



//
//	public Goal(long id, @NotBlank(message = "A Title is required") String title, Date startDate, Date targetDate,
//			BigDecimal startAmount, BigDecimal targetAmount, BigDecimal currentAmount, String description,
//			String category, String photoURL, User user) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.startDate = startDate;
//		this.targetDate = targetDate;
//		this.startAmount = startAmount;
//		this.targetAmount = targetAmount;
//		this.currentAmount = currentAmount;
//		this.description = description;
//		this.category = category;
//		this.photoURL = photoURL;
//		this.user = user;
//	}




//	public Goal(long id2, String title2, Date startDate2, Date targetDate2, BigDecimal startAmount2,
//			BigDecimal targetAmount2, BigDecimal currentAmount2, String description2, String category2,
//			String photoURL2, User user2) {
//		super();
//		this.id = id2;
//		this.title = title2;
//		this.startDate = startDate2;
//		this.targetDate = targetDate2;
//		this.startAmount = startAmount2;
//		this.targetAmount = targetAmount2;
//		this.currentAmount = currentAmount2;
//		this.description = description2;
//		this.category = category2;
//		this.photoURL = photoURL2;
//		this.user = user2;
//	}
	
}
