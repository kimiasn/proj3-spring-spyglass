package com.skillstorm.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.repositories.GoalRepository;

@Service
@Transactional
public class GoalService {

	@Autowired
	private GoalRepository goalRepository;

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	public List<GoalDto> findAllGoalsByUserId(String userId) {
//		return goalRepository.findAllGoalsByUserId(userId).stream().map(g -> g.toDto()).toList();
		return goalRepository.findAllGoalsByUserId(userId)
					.stream()
					.map(g -> g.toDto())
					.collect(Collectors.toList());
	}

	public Goal findGoalByGoalId(long goalId) {
		
		return goalRepository.findById(goalId).orElseThrow(NoSuchElementException::new);
	}

	public List<GoalDto> getAllGoalsSorted(String[] sort) {

		List<Order> orders = new ArrayList<Order>();

		if (sort[0].contains(",")) {
			// will sort more than 2 columns
			for (String sortOrder : sort) {
				// sortOrder="column, direction"
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
			}
		} else {
			// sort=[column, direction]
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}

		List<Goal> goals = goalRepository.findAll(Sort.by(orders));

		return goals.stream().map(g -> g.toDto()).collect(Collectors.toList());

	}

	/**
	 * 
	 * @param goalData The data to create a goal entity with
	 * @return The data of the newly create goal
	 */
	public GoalDto createGoal(GoalDto goalData) {
//		User user = userRepository.findById(goalData.getUserId()).orElseThrow();

		Goal goal = Goal.builder().category(goalData.getCategory()).title(goalData.getTitle())
				.startDate(goalData.getStartDate()).targetDate(goalData.getTargetDate())
				.startAmount(goalData.getStartAmount()).targetAmount(goalData.getTargetAmount())
				.currentAmount(goalData.getCurrentAmount()).description(goalData.getDescription())
				.category(goalData.getCategory()).photoURL(goalData.getPhotoURL()).userId(goalData.getUserId()).build();

		return goalRepository.save(goal).toDto();

	}

	/**
	 * 
	 * @param goalData The data to update a goal with
	 * @return the data of the updated goal
	 */
	public GoalDto updateGoal(GoalDto goalData) {
//		User user = userRepository.findById(goalData.getUserId()).orElseThrow();

		Goal goal = findGoalByGoalId(goalData.getId());
		goal.setUserId(goalData.getUserId());
		goal.setCategory(goalData.getCategory());
		goal.setCurrentAmount(goalData.getCurrentAmount());
		goal.setDescription(goalData.getDescription());
		goal.setPhotoURL(goalData.getPhotoURL());
		goal.setStartAmount(goalData.getStartAmount());
		goal.setStartDate(goalData.getStartDate());
		goal.setTargetAmount(goalData.getTargetAmount());
		goal.setTargetDate(goalData.getTargetDate());
		goal.setTitle(goalData.getTitle());

		return goalRepository.save(goal).toDto();

	}

	public void deleteGoal(Goal goal) {
		goalRepository.delete(goal);
	}
}
