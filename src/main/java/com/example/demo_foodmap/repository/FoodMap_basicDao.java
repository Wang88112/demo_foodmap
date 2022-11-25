package com.example.demo_foodmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_foodmap.entity.FoodMapShop;

@Repository  //用於資料處理(CRUD)讓SpringBoot託管
public interface FoodMap_basicDao extends JpaRepository<FoodMapShop, String> {
	
	//搜城市找店家
	public List<FoodMapShop>  findByCity(String city);
	
	//搜店評找店家
	public List<FoodMapShop> findByShopStarGreaterThanEqualOrderByShopStarDesc(double shopStar);
	
	//



}
