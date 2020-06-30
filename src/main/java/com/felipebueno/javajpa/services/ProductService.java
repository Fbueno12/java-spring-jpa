package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Product;
import com.felipebueno.javajpa.repositories.ProductRepository;
import com.felipebueno.javajpa.services.exceptions.DatabaseException;
import com.felipebueno.javajpa.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> index() {
		return repository.findAll();
	}

	public Product find(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Product store(Product product) {
		return repository.save(product);
	}

	public Product update(Long id, Product product) {
		try {
			Product entity = repository.getOne(id);

			updateData(entity, product);

			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

	private void updateData(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setDescription(product.getDescription());
		entity.setImgUrl(product.getImgUrl());
		entity.setPrice(product.getPrice());
		entity.getCategories().addAll(product.getCategories());
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
