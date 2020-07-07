package com.felipebueno.javajpa.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebueno.javajpa.entities.Order;
import com.felipebueno.javajpa.entities.OrderItem;
import com.felipebueno.javajpa.entities.User;
import com.felipebueno.javajpa.entities.payloads.OrderRequest;
import com.felipebueno.javajpa.entities.payloads.types.ItemsType;
import com.felipebueno.javajpa.repositories.OrderItemRepository;
import com.felipebueno.javajpa.repositories.OrderRepository;
import com.felipebueno.javajpa.repositories.ProductRepository;
import com.felipebueno.javajpa.repositories.UserRepository;
import com.felipebueno.javajpa.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderItemRepository orderItemrepository;

	@Autowired
	private ProductRepository productRepository;

	public List<Order> index() {
		return repository.findAll();
	}

	public Order find(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Order store(OrderRequest order) {
		try {
			
		
		User client = userRepository.getOne(order.getClient_id());
		
		if(client == null) {
			throw new ResourceNotFoundException(order.getClient_id());
		}
		
		Order newOrder = new Order();

		newOrder.setMoment(order.getMoment());
		newOrder.setOrderStatus(order.getOrderStatus());
		newOrder.setClient(client);
		
		newOrder = repository.save(newOrder);

		Double price = 0.0;
		for (ItemsType itemtype : order.getItems()) {
			var item = productRepository.getOne(itemtype.getId());
			
			if(item == null) {
				throw new ResourceNotFoundException(itemtype.getId());
			}
			
			var itemPrice = item.getPrice() * itemtype.getQuantity();
			price += itemPrice;
			
			OrderItem oi = new OrderItem(newOrder, item, itemtype.getQuantity(), price);
			oi = orderItemrepository.save(oi);
			System.out.println("PASSO DAQUI PELO MMENOS");
		}

		return repository.getOne(newOrder.getId());
		} catch (RuntimeException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	public Order update(Long id, OrderRequest order) {
		try {
			Order entity = repository.getOne(id);
			updateData(entity, order);

			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	private void updateData(Order entity, OrderRequest order) {
		User client = userRepository.getOne(order.getClient_id());
		
		entity.setClient(client);
		entity.setMoment(order.getMoment());
		entity.setPayment(order.getPayment());
		entity.setOrderStatus(order.getOrderStatus());
	}
}
