package com.felipebueno.javajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipebueno.javajpa.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
