package com.example.demo_foodmap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_map_basic")
public class FoodMapShop {
	
	@Id
	@Column(name = "shop_basic")
	private String shopName;
	
	@Column(name = "city_basic")
	private String city;

	@Column(name = "shop_star")
	private double shopStar;
	

	
	public FoodMapShop() {
		
	}
	
	public FoodMapShop (String city, String shop_basic) {
		this.city = city;
		this.shopName = shop_basic;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shop_basic) {
		this.shopName = shop_basic;
	}

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public double getShopStar() {
		return shopStar;
	}


	public void setShopStar(double shopStar) {
		this.shopStar = shopStar;
	}


	
}
