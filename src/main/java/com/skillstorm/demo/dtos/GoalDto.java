package com.skillstorm.demo.dtos;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoalDto {
	
	private long id;
	private String userId;
	private String title;
	private Date startDate;
	private Date targetDate;
	private BigDecimal startAmount;
	private BigDecimal targetAmount;
	private BigDecimal currentAmount;
	private String description;
	private String category;
	private String photoURL;
}
