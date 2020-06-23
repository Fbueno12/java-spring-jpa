package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Product;
import com.felipebueno.javajpa.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> index() {
		return repository.findAll();
	}
	
	public Product find(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
