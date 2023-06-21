package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.dtos.UserDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.services.GoalService;
import com.skillstorm.demo.services.UserService;

@RestController
@RequestMapping("/goals")
@CrossOrigin
public class GoalController {
	
	@Autowired
	private GoalService goalService;
	
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param userId The id of user 
	 * @return A list of all goals for a user id
	 */
	@GetMapping("/user/{userId}") 
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
	@PostMapping("/user/{userId}") 
	public ResponseEntity<GoalDto> createGoalByUserId(@RequestBody GoalDto goalData) {
		System.out.println("user id: " + goalData.getUserId());
		try {
			UserDto userDto = userService.findUserById(goalData.getUserId());
			System.out.println("user does not exist");
			if (userDto == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			GoalDto goalDto = goalService.createGoal(goalData);
			return new ResponseEntity<>(goalDto, HttpStatus.CREATED);
			
		} catch(Exception e){

			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<GoalDto> updateGoalByGoalId(@RequestBody GoalDto goalData) {
		try {
			
			Goal goal = goalService.findGoalByGoalId(goalData.getId());
			if (goal == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			GoalDto goalDto = goalService.updateGoal(goalData);
		
			return new ResponseEntity<>(goalDto, HttpStatus.OK);
			
		} catch(Exception e){

			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGoalByGoalId(@PathVariable long id) {
		Goal goal = goalService.findGoalByGoalId(id);
		System.out.println("goal id to delete: ");
		if (goal == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		goalService.deleteGoal(goal);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
