package com.felipebueno.javajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipebueno.javajpa.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
