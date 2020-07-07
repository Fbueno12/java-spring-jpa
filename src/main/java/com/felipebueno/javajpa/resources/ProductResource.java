package com.felipebueno.javajpa.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipebueno.javajpa.entities.Product;
import com.felipebueno.javajpa.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<Product>> index() {
		List<Product> list = service.index();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> find(@PathVariable Long id) {
		Product product = service.find(id);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<Product> store(@Valid @RequestBody Product product) {
		product = service.store(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(product.getId())
				.toUri();

		return ResponseEntity.created(uri).body(product);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
		product = service.update(id, product);
		
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
