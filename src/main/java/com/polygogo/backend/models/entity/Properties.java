package com.polygogo.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="properties")
public class Properties implements Serializable{

	@Id
	private long id;
	
	private String name;
	
	private int price;
	
	private int rental;
	
	private String color;

	public Properties() {
		
	}

	public Properties(long id, String name, int price, int rental, String color) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.rental = rental;
		this.color = color;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRental() {
		return rental;
	}

	public void setRental(int rental) {
		this.rental = rental;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
