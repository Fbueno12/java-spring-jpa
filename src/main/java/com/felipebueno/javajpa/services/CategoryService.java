package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Category;
import com.felipebueno.javajpa.repositories.CategoryRepository;
import com.felipebueno.javajpa.services.exceptions.DatabaseException;
import com.felipebueno.javajpa.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> index() {
		return repository.findAll();
	}
	
	public Category find(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	
	public Category store(Category category) {
		Category categoryExists = repository.findByName(category.getName());
		
		if(categoryExists != null) {
			throw new DatabaseException("This category already exists!");
		}
			
		return repository.save(category);
	}

	public Category update(Long id, Category category) {
		try {
			Category entity = repository.getOne(id);
			
			updateData(entity, category);
			
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}

	private void updateData(Category entity, Category category) {
		entity.setName(category.getName());		
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
}
