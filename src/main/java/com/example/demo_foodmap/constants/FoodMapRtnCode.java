package com.example.demo_foodmap.constants;

public enum FoodMapRtnCode {
	
	SUCCESSFUL("200", "Successful"),
	SHOP_REQUIRED("400", "Shop cannot be null or empty!"),
	NEW_SHOP_NAME_REQUIRED("400", "Shop name cannot be null or empty!"),
	FOOD_REQUIRED("400", "FoodName cannot be null or empty!"),
	CITY_REQUIRED("400", "City is required!"),
	STAR_REQUIRED("400", "Star is required!"),
	CITY_SHOP_REQUIRED("400", "City or Shop is required!"),
	FOOD_PRICE_FOODSTAR_REQUIRED("400", "Food name or price or food star is required!"),
	SHOP_EXISTED("403", "Shop existed!"),
	SHOP_NAME_EXISTED("403", "Shop inexisted!"),
	CITY_INEXISTED("403", "City inexisted!"),
	SHOP_FOOD_NAME_EXISTED("403", "Shop & Food inexisted!"),
	MEAL_EXISTED("400", "Shop or Food cannot be null or empty!"),
	PRICE_REQUIRED("400", "Price cannot <= 0 !"),
	FOOD_STAR_REQUIRED("400", "Food star cannot <= 0 or >5!"),
	FOODSTAR_REQUIRED("400", "Food star cannot < 0 or >5!"),
	SHOPSTAR_REQUIRED("400", "Shop star cannot < 0 or >5!"),
	FAILURE("500", "Shop is fail!");

	
	private String code;
	private String message;
	
	private FoodMapRtnCode(String code, String message) {   //此建構方法用在上面
		this.code = code;                                   //enum的建構方法只能是private
		this.message = message;
	}
	
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

}
