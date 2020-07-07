package com.felipebueno.javajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.felipebueno.javajpa.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
}
