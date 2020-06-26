package com.felipebueno.javajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipebueno.javajpa.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
