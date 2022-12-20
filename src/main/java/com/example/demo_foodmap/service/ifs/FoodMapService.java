package com.example.demo_foodmap.service.ifs;

import java.util.List;

import com.example.demo_foodmap.entity.FoodMapMeal;
import com.example.demo_foodmap.entity.FoodMapShop;
import com.example.demo_foodmap.vo.FoodMapRes;

public interface FoodMapService {

	// 1.新增店家資訊
	public FoodMapShop createfoodMap_basic(String city, String shop_basic);

	// 2.新增餐點資訊
	public FoodMapMeal createfoodMap_meal(String shop, String food, int price, int food_star);

	// 3.更新店家資訊
	public FoodMapShop updateShopInfo(String shopName, String city, String newShopName);

	// 4.更餐點資訊
	public FoodMapMeal updateMealInfo(String shop, String food, String newfoodName, int price, int food_star);

	// 5.利用城市尋找店家(可限制顯比數)
	public List<FoodMapRes> findByCity(String city, int displayAmount);

	// 6.利用店家評價查詢(依評價高到低，顯示店家資訊與餐點資訊)
	public List<FoodMapRes> findByShopStarGreaterThanEqual(double shopStar);

	// 7.利用店家評價及餐點評價查詢(依評價高到低，顯示店家資訊與餐點資訊)
	public List<FoodMapRes> findShopStarFoodStar(double shopStar, int food_star);

}
