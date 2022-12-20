package com.example.demo_foodmap.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo_foodmap.entity.FoodMapShop;
import com.example.demo_foodmap.entity.FoodMapMeal;
import com.example.demo_foodmap.entity.FoodMapMealId;
import com.example.demo_foodmap.repository.FoodMap_basicDao;
import com.example.demo_foodmap.repository.FoodMap_MealDao;
import com.example.demo_foodmap.service.ifs.FoodMapService;
import com.example.demo_foodmap.vo.FoodMapRes;

@Service
public class FoodMapServiceImpl implements FoodMapService {

	@Autowired
	private FoodMap_basicDao foodMap_basicDao;

	@Autowired
	private FoodMap_MealDao foodMap_MealDao;

	// 1.新增店家資訊
	@Override
	public FoodMapShop createfoodMap_basic(String city, String shop_basic) {

		// 判斷店家是否存在
		if (foodMap_basicDao.existsById(shop_basic)) {
			return null;
		}

		FoodMapShop basic = new FoodMapShop(city, shop_basic);
		return foodMap_basicDao.save(basic);
	}

	// 2.新增餐點資訊
	@Override
	public FoodMapMeal createfoodMap_meal(String shop, String food, int price, int food_star) {

		// 判斷店家是否存在
		if (!foodMap_basicDao.existsById(shop)) {
			return null; // return null :不存在 : return null
		}

		// 將FoodMapMeal的雙主鍵包成Optional
		FoodMapMealId foodMapMealId = new FoodMapMealId(shop, food);
		Optional<FoodMapMeal> foodMap_mealOp = foodMap_MealDao.findById(foodMapMealId);
		if (foodMap_mealOp.isPresent()) {
			return null; // return null:存在 : return null
		}

		FoodMapMeal meal = new FoodMapMeal(shop, food, price, food_star);
		foodMap_MealDao.save(meal);

		List<FoodMapMeal> meallist = foodMap_MealDao.findByShop(shop);

		int total = 0; // total來放總評價加總
		int count = 0; // 計算次數
		for (FoodMapMeal meal1 : meallist) {
			total += meal1.getFoodStar();  // total累加計算此店家每筆餐點的評價
			count++; // 計算餐點評價有幾筆
		}

		// 總評價數除次數的值(小數1位)，為店家評價
		double shopStar = (double) total / count;  // 設變數表示total / count(小數點)
		shopStar = Math.round(shopStar * 10.0) / 10.0; // 四捨五入的值，將數值定在僅小數第一位

		Optional<FoodMapShop> foodMap_basicOp = foodMap_basicDao.findById(shop); // 尋找basic店家
		foodMap_basicOp.get().setShopStar(shopStar);
		foodMap_basicDao.save(foodMap_basicOp.get()); // foodMap_basicDao.save回傳值為foodMap_basicOp.get()
		return meal;
	}

	// 3.更新 城市 店名(pk)
	@Override
	public FoodMapShop updateShopInfo(String shopName, String city, String newShopName) {

		Optional<FoodMapShop> shopOp = foodMap_basicDao.findById(shopName);
		if (shopOp.isEmpty()) {  // 判斷有無此店家名稱foodMap_basicDao.existsById(shopName)
			return null;
		}

		FoodMapShop shop = shopOp.get();
		// 判斷有無新的城市
		if (StringUtils.hasText(city)) {
			shop.setCity(city);
		}
		// 判斷有無新的店家名稱
		if (StringUtils.hasText(newShopName)) {
			foodMap_basicDao.delete(shop);
			shop.setShopName(newShopName);
			/*
			 * 連動資料庫餐點的表，將原先的商店名稱改成新的商店名稱 MealDao裡的店家的餐點放進mealList
			 * MealDao裡的店家的餐點放進mealList
			 */
			List<FoodMapMeal> mealList = foodMap_MealDao.findByShop(shopName);  
			for (FoodMapMeal meal : mealList) {
				foodMap_MealDao.delete(meal);
				meal.setShop(newShopName);
				foodMap_MealDao.save(meal);
			}
		}
		return foodMap_basicDao.save(shop);
	}

	// 4.更新 餐點 價格 餐點評價(1-5)
	@Override
	public FoodMapMeal updateMealInfo(String shop, String food, String newfoodName, int price, int food_star) {
		FoodMapMealId foodMapMealId = new FoodMapMealId(shop, food);
		Optional<FoodMapMeal> foodOp = foodMap_MealDao.findById(foodMapMealId);
		if (foodOp.isEmpty()) { // 商店、餐點不存在
			return null;
		}
		FoodMapMeal foodmeal = foodOp.get();
		foodMap_MealDao.delete(foodmeal);
		if (StringUtils.hasText(newfoodName)) {
			foodmeal.setFood(newfoodName);
		}
		if (price > 0) {
			foodmeal.setPrice(price);
		}
		if (food_star > 0) {
			foodmeal.setFoodStar(food_star);

			// 連動資料庫餐點的表，將原先的餐點評價改成新的餐點評價
			List<FoodMapMeal> meallist = foodMap_MealDao.findByShop(shop); // 尋找meal店家
			int total = 0;
			int count = 0;
			for (FoodMapMeal meal1 : meallist) { 
				total += meal1.getFoodStar();  // total累加計算此店家餐點評價
				count++;  // 計算餐點評價有幾筆 
			}

			// 設變數shopStar表示total / count(小數點)
			double shopStar = (double) (total + food_star) / (count + 1); 
			shopStar = Math.round(shopStar * 10.0) / 10.0;

			Optional<FoodMapShop> foodMap_basicOp = foodMap_basicDao.findById(shop); // 尋找basic店家
			foodMap_basicOp.get().setShopStar(shopStar);
			foodMap_basicDao.save(foodMap_basicOp.get());
		}
		return foodMap_MealDao.save(foodmeal);
	}

	// 5.搜城市找店家(顯示店家、店家評價、餐點、價格、餐點評價)
	@Override
	public List<FoodMapRes> findByCity(String city, int displayAmount) {
		List<FoodMapRes> resList = new ArrayList<FoodMapRes>(); // 型態
		if (foodMap_basicDao.findByCity(city).isEmpty()) {  // 判斷是否有此城市
			return null;
		}

		List<FoodMapShop> baiscList = foodMap_basicDao.findByCity(city);  // 找城市
		if (displayAmount == 0 || displayAmount > baiscList.size()) {  // 顯示筆數限制0跟超過都顯示全部
			displayAmount = baiscList.size();
		}
		baiscList = baiscList.subList(0, displayAmount);  // 給予數值限制顯示筆數

		List<String> shopList = new ArrayList<>();
		for (FoodMapShop shop : baiscList) {
			shopList.add(shop.getShopName());
		}

		List<FoodMapMeal> mealList = foodMap_MealDao.findByShopIn(shopList);

		for (FoodMapShop shop : baiscList) { 
			FoodMapRes res = new FoodMapRes();
			List<FoodMapMeal> eachShopMealList = new ArrayList<>();
			for (FoodMapMeal meal : mealList) {
				if (shop.getShopName().equalsIgnoreCase(meal.getShop())) {
					eachShopMealList.add(meal);
				}
			}
			res.setMealList(eachShopMealList); // 尋找店家並取得所有餐點資訊
			res.setShop(shop); //
			resList.add(res);
		}
		return resList;
	}

	// 6.搜店評找店家(顯示城市、店名、店評、餐點價格、餐頂評價)
	@Override
	public List<FoodMapRes> findByShopStarGreaterThanEqual(double shopStar) {
		List<FoodMapRes> resList = new ArrayList<FoodMapRes>();
		List<FoodMapShop> shopList = foodMap_basicDao.findByShopStarGreaterThanEqualOrderByShopStarDesc(shopStar);// 用店家評價找店家(排序)
		List<String> shopNameList = new ArrayList<>();
		for (FoodMapShop shop : shopList) {
			shopNameList.add(shop.getShopName()); // 將找到的店家放入shopNameList
		}

		List<FoodMapMeal> mealList = foodMap_MealDao.findByShopInOrderByFoodStarDesc(shopNameList); // 依店名找到餐點

		for (FoodMapShop shop : shopList) {
			FoodMapRes res = new FoodMapRes();
			List<FoodMapMeal> eachShopMealList = new ArrayList<>();
			for (FoodMapMeal meal : mealList) {
				if (shop.getShopName().equalsIgnoreCase(meal.getShop())) { // 找相同店名
					eachShopMealList.add(meal); // 將每一筆餐點資訊放入eachShopMealList
				}
			}
			res.setShop(shop);
			res.setMealList(eachShopMealList);
			resList.add(res);
		}
		return resList;
	}

	// 7.用店評+餐評來搜尋店家
	@Override
	public List<FoodMapRes> findShopStarFoodStar(double shopStar, int food_star) {
		List<FoodMapRes> resList = new ArrayList<FoodMapRes>();
		List<FoodMapShop> shopList = foodMap_basicDao.findByShopStarGreaterThanEqualOrderByShopStarDesc(shopStar); // 找店家評價
		List<String> shopNameList = new ArrayList<>();
		for (FoodMapShop shop : shopList) {
			shopNameList.add(shop.getShopName());
		}
		List<FoodMapMeal> mealList = foodMap_MealDao
				.findByShopInAndFoodStarGreaterThanEqualOrderByFoodStarDesc(shopNameList, food_star);

		for (FoodMapShop shop : shopList) {
			FoodMapRes res = new FoodMapRes();
			List<FoodMapMeal> eachShopMealList = new ArrayList<>();
			for (FoodMapMeal meal : mealList) {
				if (shop.getShopName().equalsIgnoreCase(meal.getShop())) { // 找相同店名
					eachShopMealList.add(meal); // 將每一筆餐點資訊放入eachShopMealList
				}
			}
			res.setMealList(eachShopMealList);
			res.setShop(shop);
			resList.add(res);
		}
		return resList;
	}

}
