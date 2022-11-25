package com.example.demo_foodmap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "food_map_meal")
@IdClass(FoodMapMealId.class)
public class FoodMapMeal {

	@Id
	@Column(name = "shop_in")
	private String shop;

	@Id
	@Column(name = "food")
	private String food;

	@Column(name = "price")
	private int price;

	@Column(name = "food_star")
	private int foodStar;

	public FoodMapMeal() {

	}

	public FoodMapMeal(String shop_in, String food, int price, int foodStar) {
		this.shop = shop_in;
		this.food = food;
        this.price = price;
        this.foodStar = foodStar;
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

	public int getFoodStar() {
		return foodStar;
	}

	public void setFoodStar(int foodStar) {
		this.foodStar = foodStar;
	}

	
}
