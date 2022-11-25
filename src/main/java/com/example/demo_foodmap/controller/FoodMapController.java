package com.example.demo_foodmap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_foodmap.constants.FoodMapRtnCode;
import com.example.demo_foodmap.entity.FoodMapShop;
import com.example.demo_foodmap.entity.FoodMapMeal;
import com.example.demo_foodmap.service.ifs.FoodMapService;
import com.example.demo_foodmap.vo.FoodMapReq;
import com.example.demo_foodmap.vo.FoodMapRes;

@CrossOrigin
@RestController
public class FoodMapController {

	@Autowired
	private FoodMapService foodMapService;

	// 1.�Ы� ���� ���W(pk)
	@PostMapping(value = "/api/createFoodMapBasic")
	public FoodMapRes createfoodMapBasic(@RequestBody FoodMapReq req) {
		FoodMapRes checkResult = checkParam(req);
		if (checkResult != null) { // �w�N�P�_���t�g�p����k(�U��)
			return checkResult;
		}
		FoodMapShop food = foodMapService.createfoodMap_basic(req.getCity(), req.getShop_basic());
		if (food == null) {
			return new FoodMapRes(FoodMapRtnCode.SHOP_EXISTED.getMessage());
		}
		return new FoodMapRes(food, FoodMapRtnCode.SUCCESSFUL.getMessage());
	}

	// 1.�Ы� ���� ���W(pk) StringUtils.hasText��k
	private FoodMapRes checkParam(FoodMapReq req) {
		// account,pwd,name,cannot be null or empty
		if (!StringUtils.hasText(req.getCity())) {
			return new FoodMapRes(FoodMapRtnCode.CITY_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getShop_basic())) {
			return new FoodMapRes(FoodMapRtnCode.SHOP_REQUIRED.getMessage());
		}
		return null;

	}

	// 2. �Ы� ���W �\�I ���� �\�I����(1-5)
	@PostMapping(value = "/api/createfoodMap_meal")
	public FoodMapRes createfoodMap_meal(@RequestBody FoodMapReq req) {
		if (req.getFood_star() <= 0 || req.getFood_star() > 5) {
			return new FoodMapRes(FoodMapRtnCode.FOOD_STAR_REQUIRED.getMessage());
		}
		if (req.getPrice() <= 0) {
			return new FoodMapRes(FoodMapRtnCode.PRICE_REQUIRED.getMessage());
		}

		FoodMapRes checkResult = checkParam1(req);
		if (checkResult != null) { // �w�N�P�_���t�g�p����k(�U��)
			return checkResult;
		}
		FoodMapMeal food = foodMapService.createfoodMap_meal(req.getShop(), req.getFood(), req.getPrice(),
				req.getFood_star());
		if (food == null) {
			return new FoodMapRes(FoodMapRtnCode.MEAL_EXISTED.getMessage());
		}
		foodMapService.createfoodMap_meal(req.getShop(), req.getFood(), req.getPrice(), req.getFood_star());
		return new FoodMapRes(food, FoodMapRtnCode.SUCCESSFUL.getMessage());

	}

	// 2. �Ы� ���W �\�I ���� �\�I����(1-5) StringUtils.hasText��k
	private FoodMapRes checkParam1(FoodMapReq req) {

		if (!StringUtils.hasText(req.getShop())) {
			return new FoodMapRes(FoodMapRtnCode.SHOP_REQUIRED.getMessage());
		}
		if (!StringUtils.hasText(req.getFood())) {
			return new FoodMapRes(FoodMapRtnCode.FOOD_REQUIRED.getMessage());
		}
		return null;
	}

	// 3.��s���a�W�١B����
	@PostMapping(value = "/api/updateShopInfo")
	public FoodMapRes updateShopInfo(@RequestBody FoodMapReq req) {
		FoodMapShop foodMapShop = foodMapService.updateShopInfo(req.getShop_basic(), req.getCity(),
				req.getNewShopName());
		if (foodMapShop == null) {
			return new FoodMapRes(FoodMapRtnCode.SHOP_NAME_EXISTED.getMessage());
		}
		if (!StringUtils.hasText(req.getCity()) && !StringUtils.hasText(req.getNewShopName())) {
			return new FoodMapRes(FoodMapRtnCode.CITY_SHOP_REQUIRED.getMessage());
		}
//		res.setShop(foodMapShop);
//		res.setMessage(FoodMapRtnCode.SUCCESSFUL.getMessage());
		return new FoodMapRes(foodMapShop, FoodMapRtnCode.SUCCESSFUL.getMessage());
	}

	// 4.��s�\�I�W�١B����B�\�I�����A�s�ʩ��a����
	@PostMapping(value = "/api/updateMealInfo")
	public FoodMapRes updateMealInfo(@RequestBody FoodMapReq req) {
		FoodMapMeal FoodMapMeal = foodMapService.updateMealInfo(req.getShop(), req.getFood(), req.getNewFoodName(),
				req.getPrice(), req.getFood_star());
		if (!StringUtils.hasText(req.getNewFoodName()) && req.getPrice() <= 0 && req.getFood_star() <= 0) {
			return new FoodMapRes(FoodMapRtnCode.FOOD_PRICE_FOODSTAR_REQUIRED.getMessage());
		}

		if (FoodMapMeal == null) {
			return new FoodMapRes(FoodMapRtnCode.SHOP_FOOD_NAME_EXISTED.getMessage());
		}
		if (req.getPrice() < 0) {
			return new FoodMapRes(FoodMapRtnCode.PRICE_REQUIRED.getMessage());
		}
		if (req.getFood_star() < 0 || req.getFood_star() > 5) {
			return new FoodMapRes(FoodMapRtnCode.FOOD_STAR_REQUIRED.getMessage());
		}
		return new FoodMapRes(FoodMapMeal, FoodMapRtnCode.SUCCESSFUL.getMessage());
	}

	// 5.�j�����䩱�a(��ܩ��a�B���a�����B�\�I�B����B�\�I����)
	@PostMapping(value = "/api/findByCity")
	public FoodMapRes findByCity(@RequestBody FoodMapReq req) {
		List<FoodMapRes> resList = foodMapService.findByCity(req.getCity(), req.getDisplayAmount());
		if (resList == null) {
			return new FoodMapRes(FoodMapRtnCode.CITY_INEXISTED.getMessage());
		}
		return new FoodMapRes(resList, FoodMapRtnCode.SUCCESSFUL.getMessage());
	}

	// 6.�j�����䩱�a(��ܫ����B���W�B�����B�\�I����B�\������)
	@PostMapping(value = "/api/findByShopStarGreaterThanEqual")
	public FoodMapRes findByShopStarGreaterThanEqual(@RequestBody FoodMapReq req) {
		if (req.getShopStar() < 0 || req.getShopStar() > 5) {
			return new FoodMapRes(FoodMapRtnCode.SHOPSTAR_REQUIRED.getMessage());
		}
		List<FoodMapRes> resList = foodMapService.findByShopStarGreaterThanEqual(req.getShopStar());
		if (resList.isEmpty()) {
			return new FoodMapRes(resList, "�S���j��" + req.getShopStar() + "�P����!");
		}
		
		return new FoodMapRes(resList, FoodMapRtnCode.SUCCESSFUL.getMessage());
	}

	// 7.�Ω���+�\���ӷj�M���a
	@PostMapping(value = "/api/findShopStarFoodStar")
	public FoodMapRes findShopStarFoodStar(@RequestBody FoodMapReq req) {
		if (req.getShopStar() < 0 || req.getShopStar() > 5) {
			return new FoodMapRes(FoodMapRtnCode.SHOPSTAR_REQUIRED.getMessage());
		}

		if (req.getFood_star() < 0 || req.getFood_star() > 5) {
			return new FoodMapRes(FoodMapRtnCode.FOODSTAR_REQUIRED.getMessage());
		}

		List<FoodMapRes> resList = foodMapService.findShopStarFoodStar(req.getShopStar(), req.getFood_star());

		if (resList.isEmpty()) {
			return new FoodMapRes(resList, "�S���j��" + req.getShopStar() + "�P����!");
		}

		return new FoodMapRes(resList, FoodMapRtnCode.SUCCESSFUL.getMessage());
	}
}
