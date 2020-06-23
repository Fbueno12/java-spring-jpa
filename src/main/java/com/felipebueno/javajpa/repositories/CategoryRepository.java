package com.felipebueno.javajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipebueno.javajpa.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
