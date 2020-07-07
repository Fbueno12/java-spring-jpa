package com.felipebueno.javajpa.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipebueno.javajpa.entities.Order;
import com.felipebueno.javajpa.entities.payloads.OrderRequest;
import com.felipebueno.javajpa.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> index() {
		List<Order> list = service.index();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> find(@PathVariable Long id) {
		Order order = service.find(id);
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<Order> store(@Valid @RequestBody OrderRequest order) {
		var neworder = service.store(order);

		return ResponseEntity.ok().body(neworder);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @Valid @RequestBody OrderRequest order){
		Order neworder = service.update(id, order);
		
		return ResponseEntity.ok().body(neworder);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
