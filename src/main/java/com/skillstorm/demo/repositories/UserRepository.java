package com.skillstorm.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	@Query(SELECT u FROM User u WHERE u.id = ?1")
//	Optional<User> findById(long userId);
	
}
