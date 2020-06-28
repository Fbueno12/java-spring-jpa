package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Category;
import com.felipebueno.javajpa.repositories.CategoryRepository;

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
}
