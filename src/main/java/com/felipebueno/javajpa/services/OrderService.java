package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Order;
import com.felipebueno.javajpa.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> index() {
		return repository.findAll();
	}
	
	public Order find(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
