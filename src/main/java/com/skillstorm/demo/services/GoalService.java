package com.skillstorm.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.repositories.GoalRepository;
import com.skillstorm.demo.repositories.UserRepository;

@Service
@Transactional
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	private UserRepository userRepository;
	
	public List<GoalDto> findAllGoalsById(Long userId) {
//		Optional<Goal> goalOptional = goalRepository.findById(userId);
//        if (goalOptional.isPresent()) {
//            Goal goal = goalOptional.get();
//            return goal.stream()
//                    .map(Goal::toDto)
//                    .collect(Collectors.toList());
//        }
        return List.of(); 
//				.stream()
//				.map(t -> t.toDto())
//				.toList();
	}
	
	/**
	 * 
	 * @param goalData The data to create a goal entity with 
	 * @return The data of the newly create goal
	 */
	public GoalDto createGoal(GoalDto goalData) {
		User user = userRepository.findById(goalData.getUserId());
		Goal goal = new Goal(goalData.getId(), goalData.getTitle(), 
				goalData.getStartDate(), goalData.getTargetDate(),
				goalData.getStartAmount(), goalData.getTargetAmount(), 
				goalData.getCurrentAmount(), goalData.getDescription(), 
				goalData.getCategory(), goalData.getPhotoURL(), user);
				
		return goalRepository.save(goal).toDto();
	}
}

// GoalDto(long, String, Date, Date, BigDecimal, BigDecimal, BigDecimal, String, String, String)