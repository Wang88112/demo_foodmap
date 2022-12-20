package com.example.demo_foodmap.vo;

import java.util.List;

import com.example.demo_foodmap.entity.FoodMapMeal;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodMapResList {

	private FoodMapMeal foodMapMeal; 
	
	private List<FoodMapRes> resList;  
	
	private String city;

	private String message;

	
//==========================================	
	public FoodMapResList() {

	}

	public FoodMapResList(String message) {
		this.message = message;
	}

	public FoodMapMeal getFoodMapMeal() {
		return foodMapMeal;
	}

	public void setFoodMapMeal(FoodMapMeal foodMapMeal) {
		this.foodMapMeal = foodMapMeal;
	}

	public List<FoodMapRes> getResList() {
		return resList;
	}

	public void setResList(List<FoodMapRes> resList) {
		this.resList = resList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
