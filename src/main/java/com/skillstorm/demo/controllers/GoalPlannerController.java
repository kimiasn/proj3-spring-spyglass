package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.services.GoalService;

@RestController
@RequestMapping("/goals")
@CrossOrigin
public class GoalPlannerController {
	
	@Autowired
	private GoalService goalService;

	@GetMapping("/{userId}") 
	public ResponseEntity<List<GoalDto>> getAllGoalsByUserId(@PathVariable long userId) {
		try {
			List<GoalDto> goals = goalService.findAllGoalsByUserId(userId);
			
			if (goals.isEmpty() ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(goals, HttpStatus.FOUND);
			
		} catch(Exception e){
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	};
	
	/**
	 * 
	 * @param userId The id of the goal's user
	 * @param goalData The data to create a new goal
	 * @return The data of the newly created goal
	 */
	@PostMapping
	public ResponseEntity<GoalDto> createGoalByUserId(@RequestBody GoalDto goalData) {
		GoalDto goal = goalService.createGoal(goalData);
		return new ResponseEntity<>(goal, HttpStatus.CREATED);
	}
}
