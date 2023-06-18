package com.skillstorm.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>  {
	
	@Query("select g from Goal g where g.user.id = ?1")
	public List<Goal> findAllGoalsByUserId(long id);
}
