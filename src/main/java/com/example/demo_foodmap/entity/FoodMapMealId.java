package com.example.demo_foodmap.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FoodMapMealId implements Serializable {

	private String shop;

	private String food;

	public FoodMapMealId() {

	}

	public FoodMapMealId(String Shop, String food) {
		this.shop = Shop;
		this.food = food;
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

	
}
