package com.example.demo_foodmap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foodmap")
public class Foodmap {
	
	@Id
	@Column(name = "city")
	private String city;
	
	@Id
	@Column(name = "shop")
	private String shop;
	
	@Id
	@Column(name = "food")
	private String food;

	@Column(name = "price")
	private int price;
	
	@Column(name = "star")
	private int star;
	
	public Foodmap() {
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
	
}
