package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.User;
import com.felipebueno.javajpa.repositories.UserRepository;
import com.felipebueno.javajpa.services.exceptions.DatabaseException;
import com.felipebueno.javajpa.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> index() {
		return repository.findAll();
	}
	
	public User find(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User store(User user) {
		
		User emailExists = repository.findByEmail(user.getEmail());
		
		if(emailExists != null) {
			throw new DatabaseException("Email already exists!");
		}
			
		return repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		try {
			User entity = repository.getOne(id);
			
			updateData(entity, user);
			
			return repository.save(entity);			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
	
}
