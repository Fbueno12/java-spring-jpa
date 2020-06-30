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

import com.felipebueno.javajpa.entities.Category;
import com.felipebueno.javajpa.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> index() {
		List<Category> list = service.index();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> find(@PathVariable Long id) {
		Category category = service.find(id);
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public ResponseEntity<Category> store(@Valid @RequestBody Category category) {
		category = service.store(category);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).body(category);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category category) {
		category = service.update(id, category);
		
		return ResponseEntity.ok().body(category);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}	
