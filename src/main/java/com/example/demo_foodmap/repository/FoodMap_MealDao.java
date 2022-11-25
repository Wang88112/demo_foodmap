package com.example.demo_foodmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_foodmap.entity.FoodMapMeal;
import com.example.demo_foodmap.entity.FoodMapMealId;

@Repository  //�Ω��ƳB�z(CRUD)��SpringBoot�U��
public interface FoodMap_MealDao extends JpaRepository<FoodMapMeal, FoodMapMealId>{

	public List<FoodMapMeal> findByShop(String shop);
	
	public List<FoodMapMeal> findByShopIn(List<String> shopList);
	
	public List<FoodMapMeal> findByShopInOrderByFoodStarDesc(List<String> shopNameList);
	
	public List<FoodMapMeal> findByShopInAndFoodStarGreaterThanEqualOrderByFoodStarDesc(List<String> shopNameList
			,int foodStar);
	
	public List<FoodMapMeal> findAllByFood(String food);
	
}
