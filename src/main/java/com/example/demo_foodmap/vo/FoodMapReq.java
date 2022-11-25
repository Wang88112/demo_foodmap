package com.example.demo_foodmap.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FoodMapReq {

	@JsonProperty("shop_basic")
	private String shop_basic;

	@JsonProperty("city")
	private String city;

	@JsonProperty("shopStar")
	private double shopStar;

	@JsonProperty("shop")
	private String shop;

	@JsonProperty("food")
	private String food;

	@JsonProperty("price")
	private int price;

	@JsonProperty("food_star")
	private int food_star;
	
	@JsonProperty("displayAmount")
	private int displayAmount;
	
	@JsonProperty("newFoodName")
	private String newFoodName;
	
	@JsonProperty("newShopName")
	private String newShopName;

	public FoodMapReq() {

	}

	public String getShop_basic() {
		return shop_basic;
	}

	public void setShop_basic(String shop_basic) {
		this.shop_basic = shop_basic;
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

	public int getFood_star() {
		return food_star;
	}

	public void setFood_star(int food_star) {
		this.food_star = food_star;
	}

	public int getDisplayAmount() {
		return displayAmount;
	}

	public void setDisplayAmount(int displayAmount) {
		this.displayAmount = displayAmount;
	}


	public String getNewShopName() {
		return newShopName;
	}

	public void setNewShopName(String newShopName) {
		this.newShopName = newShopName;
	}

	public double getShopStar() {
		return shopStar;
	}

	public void setShopStar(double shopStar) {
		this.shopStar = shopStar;
	}

	public String getNewFoodName() {
		return newFoodName;
	}

	public void setNewFoodName(String newFoodName) {
		this.newFoodName = newFoodName;
	}



}
