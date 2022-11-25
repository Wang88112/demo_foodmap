package com.example.demo_foodmap.service.ifs;

import java.util.List;

import com.example.demo_foodmap.entity.FoodMapMeal;
import com.example.demo_foodmap.entity.FoodMapShop;
import com.example.demo_foodmap.vo.FoodMapRes;

public interface FoodMapService {

	// 1.���� ���W(pk) ���a����(1-5)
	public FoodMapShop createfoodMap_basic(String city, String shop_basic);

	// 2.���W �\�I ���� �\�I����(1-5)
	public FoodMapMeal createfoodMap_meal(String shop, String food, int price, int food_star);

	// 3.��s ���� ���W(pk)
	public FoodMapShop updateShopInfo(String shopName, String city, String newShopName);

	// 4.��s �\�I ���� �\�I����(1-5)
	public FoodMapMeal updateMealInfo(String shop, String food, String newfoodName, int price, int food_star);

	// 5.�j�����䩱�a(��ܩ��a�B���a�����B�\�I�B����B�\�I����)
	public List<FoodMapRes> findByCity(String city, int displayAmount);

	// 6.�j�����䩱�a(��ܫ����B���W�B�����B�\�I����B�\������)
	public List<FoodMapRes> findByShopStarGreaterThanEqual(double shopStar);

	// 7.�Ω���+�\���ӷj�M���a
	public List<FoodMapRes> findShopStarFoodStar(double shopStar, int food_star);

}
