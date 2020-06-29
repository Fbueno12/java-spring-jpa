package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Category;
import com.felipebueno.javajpa.repositories.CategoryRepository;
import com.felipebueno.javajpa.services.exceptions.DatabaseException;

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
}
