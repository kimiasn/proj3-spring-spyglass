package com.skillstorm.demo.controllers;

import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.demo.dtos.GoalDto;
import com.skillstorm.demo.models.Goal;
import com.skillstorm.demo.services.GoalService;
//import com.skillstorm.demo.services.UserService;

@RestController
@RequestMapping("/goals")
@CrossOrigin(allowCredentials = "true", originPatterns = {"http://localhost:5173", "http://sylvia-spyglass2.s3-website-us-east-1.amazonaws.com"})
public class GoalController {

	@Autowired
	private GoalService goalService;

//	@Autowired
//	private UserService userService;

	@GetMapping()
//	public Map<String, Object> getAllGoalsByUserId(@AuthenticationPrincipal OAuth2User oAuthUser) {
//		Map<String, Object> user =  oAuthUser.getAttributes();
//		System.out.println("user sub/id: " + user.get("sub"));
//		return user;
	public ResponseEntity<List<GoalDto>> findAllGoalsByUserId(@AuthenticationPrincipal OAuth2User user) {
//		System.out.println("in goalControler findalluser " + user.getAttributes().get("sub"));
		try {
			List<GoalDto> goals = goalService.findAllGoalsByUserId((String) user.getAttributes().get("sub"));

			if (goals.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(goals, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	};
	


	/**
	 * 
	 * @param id The id of the goal
	 * @return a single goal
	 */
	@GetMapping("goal/{goalId}")
	public ResponseEntity<GoalDto> findGoalById(@PathVariable long goalId) {
		try {
			Goal goal = goalService.findGoalByGoalId(goalId);

			if (goal == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(goal.toDto(), HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	};

	@GetMapping("{userid}/sorted")
	public ResponseEntity<List<GoalDto>> findAllGoalsSorted(@RequestParam(defaultValue = "id, category") String[] sort) {
		try {
			List<GoalDto> goalsDto = goalService.getAllGoalsSorted(sort);

			if (goalsDto.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(goalsDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param userId   The id of the goal's user
	 * @param goalData The data to create a new goal
	 * @return The data of the newly created goal
	 */
	@PostMapping()
	public ResponseEntity<GoalDto> createGoal(@RequestBody GoalDto goalData) {

		try {
System.out.println("reached createGoal");
			GoalDto goalDto = goalService.createGoal(goalData);
			return new ResponseEntity<>(goalDto, HttpStatus.CREATED);

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("goal/{id}")
	public ResponseEntity<GoalDto> updateGoal(@PathVariable long id, @RequestBody GoalDto goalData) {
		try {

			Goal goal = goalService.findGoalByGoalId(id);
			if (goal == null) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			GoalDto goalDto = goalService.updateGoal(goalData);

			return new ResponseEntity<>(goalDto, HttpStatus.OK);

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("goal/{id}")
	public ResponseEntity<Void> deleteGoal(@PathVariable long id) {
		Goal goal = goalService.findGoalByGoalId(id);
		System.out.println("goal id to delete: ");
		if (goal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		goalService.deleteGoal(goal);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
