package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.User;
import com.felipebueno.javajpa.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> index() {
		return repository.findAll();
	}
	
	public User find(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User store(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
