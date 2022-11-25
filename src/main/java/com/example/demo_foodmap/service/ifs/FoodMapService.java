package com.example.demo_foodmap.service.ifs;

import java.util.List;

import com.example.demo_foodmap.entity.FoodMapMeal;
import com.example.demo_foodmap.entity.FoodMapShop;
import com.example.demo_foodmap.vo.FoodMapRes;

public interface FoodMapService {

	// 1.城市 店名(pk) 店家評價(1-5)
	public FoodMapShop createfoodMap_basic(String city, String shop_basic);

	// 2.店名 餐點 價格 餐點評價(1-5)
	public FoodMapMeal createfoodMap_meal(String shop, String food, int price, int food_star);

	// 3.更新 城市 店名(pk)
	public FoodMapShop updateShopInfo(String shopName, String city, String newShopName);

	// 4.更新 餐點 價格 餐點評價(1-5)
	public FoodMapMeal updateMealInfo(String shop, String food, String newfoodName, int price, int food_star);

	// 5.搜城市找店家(顯示店家、店家評價、餐點、價格、餐點評價)
	public List<FoodMapRes> findByCity(String city, int displayAmount);

	// 6.搜店評找店家(顯示城市、店名、店評、餐點價格、餐頂評價)
	public List<FoodMapRes> findByShopStarGreaterThanEqual(double shopStar);

	// 7.用店評+餐評來搜尋店家
	public List<FoodMapRes> findShopStarFoodStar(double shopStar, int food_star);

}
