package com.skillstorm.demo.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.models.Goal.GoalBuilder;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.repositories.GoalRepository;
import com.skillstorm.demo.repositories.UserRepository;

@Service
@Transactional
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<GoalDto> findAllGoalsByUserId(long userId) {
		return goalRepository.findAllGoalsByUserId(userId)
				.stream()
				.map(g -> g.toDto())
				.toList();
	}
	
	public Goal findGoalByGoalId(long goalId) {
		Goal goal = goalRepository.findById(goalId).orElseThrow();
		return goal;
	}
	/**
	 * 
	 * @param goalData The data to create a goal entity with 
	 * @return The data of the newly create goal
	 */
	public GoalDto createGoal(GoalDto goalData) {
		User user = userRepository.findById(goalData.getUserId())
				.orElseThrow();
		
		Goal goal = Goal.builder().category(goalData.getCategory())
				.title(goalData.getTitle())
				.startDate(goalData.getStartDate())
				.targetDate(goalData.getTargetDate())
				.startAmount(goalData.getStartAmount())
				.targetAmount(goalData.getTargetAmount())
				.currentAmount(goalData.getCurrentAmount())
				.description(goalData.getDescription())
				.category(goalData.getCategory())
				.photoURL(goalData.getPhotoURL())
				.user(user)
				.build();
	

		return goalRepository.save(goal).toDto();

	}
	
	/**
	 * 
	 * @param goalData The data to update a goal with
	 * @return the data of the updated goal
	 */
	public GoalDto updateGoal(GoalDto goalData) {
		User user = userRepository.findById(goalData.getUserId())
				.orElseThrow();
		
		Goal goal = findGoalByGoalId(goalData.getId());
		goal.setCategory(goalData.getCategory());
		goal.setCurrentAmount(goalData.getCurrentAmount());
		goal.setDescription(goalData.getDescription());
		goal.setPhotoURL(goalData.getPhotoURL());
		goal.setStartAmount(goalData.getStartAmount());
		goal.setStartDate(goalData.getStartDate());
		goal.setTargetAmount(goalData.getTargetAmount());
		goal.setTargetDate(goalData.getTargetDate());
		goal.setTitle(goalData.getTitle());
		goal.setUser(user);

		return goalRepository.save(goal).toDto();

	}
	
	public void deleteGoal(Goal goal) {
		goalRepository.delete(goal);
	}
}
