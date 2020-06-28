package com.felipebueno.javajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipebueno.javajpa.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}
