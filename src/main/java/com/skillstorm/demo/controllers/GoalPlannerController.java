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

	@GetMapping("/${userId}") 
	public ResponseEntity<List<GoalDto>> getAllGoals(@PathVariable long userId) {
		List<GoalDto> goals = goalService.findAllGoalsById(userId);
		return new ResponseEntity<>(goals, HttpStatus.FOUND);
	};
	
	@PostMapping
	public ResponseEntity<GoalDto> createGoal(@RequestBody GoalDto goalData) {
		GoalDto goal = goalService.createGoal(goalData);
		return new ResponseEntity<>(goal, HttpStatus.CREATED);
	}
}
