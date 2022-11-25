package com.example.demo_foodmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_foodmap.entity.FoodMapShop;

@Repository  //�Ω��ƳB�z(CRUD)��SpringBoot�U��
public interface FoodMap_basicDao extends JpaRepository<FoodMapShop, String> {
	
	//�j�����䩱�a
	public List<FoodMapShop>  findByCity(String city);
	
	//�j�����䩱�a
	public List<FoodMapShop> findByShopStarGreaterThanEqualOrderByShopStarDesc(double shopStar);
	
	//



}
