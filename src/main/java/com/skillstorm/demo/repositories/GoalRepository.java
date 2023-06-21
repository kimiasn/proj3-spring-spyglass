package com.skillstorm.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>  {
	
	@Query("select g from Goal g where g.user.id = ?1")
	public List<Goal> findAllGoalsByUserId(long id);
	
	Page<Goal> findByCategory(String category, Pageable pagable);
	
	List<Goal> findByTitleContaining(String title, Sort sort);
}

// https://www.bezkoder.com/spring-data-sort-multiple-columns/