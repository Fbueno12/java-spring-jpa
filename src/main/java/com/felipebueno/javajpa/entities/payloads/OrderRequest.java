package com.felipebueno.javajpa.entities.payloads;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.felipebueno.javajpa.entities.enums.OrderStatus;
import com.felipebueno.javajpa.entities.payloads.types.ItemsType;

public class OrderRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Instant moment;
	
	@NotNull
	private OrderStatus orderStatus;
	
	@NotNull
	private Long client_id;
	
	private List<ItemsType> items;
	
	public OrderRequest() {}
	
	public OrderRequest(Instant moment, OrderStatus orderStatus, Long client_id, List<ItemsType> items) {
		super();
		this.moment = moment;
		this.orderStatus = orderStatus;
		this.client_id = client_id;
		this.items = items;
	}



	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getClient_id() {
		return client_id;
	}
	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}
	public List<ItemsType> getItems() {
		return items;
	}
	public void setItems(List<ItemsType> items) {
		this.items = items;
	}
	
	
	
}
