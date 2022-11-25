package com.example.demo_foodmap.vo;

import com.example.demo_foodmap.entity.FoodMapShop;

import java.util.List;

import com.example.demo_foodmap.entity.FoodMapMeal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FoodMapRes {

	@JsonProperty("shop")
	private FoodMapShop shop;

	@JsonProperty("meal")
	private FoodMapMeal meal;
	
	private List<FoodMapMeal> mealList;
	
	private List<FoodMapShop> shopList;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private double shopStar;

	private String message;
	
	private FoodMapResList foodMapResList;
	
	private List<FoodMapRes> resList;

	public FoodMapRes() {

	}


	public FoodMapRes(String message) {
		this.message = message;
	}
	
	public FoodMapRes(List<FoodMapRes> resList, String message) {
        this.resList = resList;
        this.message = message;
	}
	
	public FoodMapRes(FoodMapResList foodMapResList, String message) {
        this.foodMapResList = foodMapResList;
        this.message = message;
	}
	
	public FoodMapRes(FoodMapShop foodMap_basi, String message) {
		this.shop = foodMap_basi;
		this.message = message;
	}

	public FoodMapRes(FoodMapMeal foodMap_Meal, String message) {
		this.meal = foodMap_Meal;
		this.message = message;
	}

	public FoodMapShop getShop() {
		return shop;
	}

	public void setShop(FoodMapShop shop) {
		this.shop = shop;
	}

	public FoodMapMeal getMeal() {
		return meal;
	}

	public void setMeal(FoodMapMeal meal) {
		this.meal = meal;
	}

	public List<FoodMapMeal> getMealList() {
		return mealList;
	}

	public void setMealList(List<FoodMapMeal> mealList) {
		this.mealList = mealList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FoodMapShop> getShopList() {
		return shopList;
	}

	public void setShopList(List<FoodMapShop> shopList) {
		this.shopList = shopList;
	}

	public double getShopStar() {
		return shopStar;
	}

	public void setShopStar(double shopStar) {
		this.shopStar = shopStar;
	}

	public FoodMapResList getFoodMapResList() {
		return foodMapResList;
	}

	public void setFoodMapResList(FoodMapResList foodMapResList) {
		this.foodMapResList = foodMapResList;
	}


	public List<FoodMapRes> getResList() {
		return resList;
	}


	public void setResList(List<FoodMapRes> resList) {
		this.resList = resList;
	}

	

}
