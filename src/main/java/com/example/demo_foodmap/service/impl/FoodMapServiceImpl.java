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

	// 1.���� ���W(pk) ���a����(1-5)
	@Override
	public FoodMapShop createfoodMap_basic(String city, String shop_basic) {

		// �P�_��J�����a�O�_�s�b
		if (foodMap_basicDao.existsById(shop_basic)) {
			return null;
		}

		// �s�J�s�����a��T
		FoodMapShop basic = new FoodMapShop(city, shop_basic);
		return foodMap_basicDao.save(basic);
	}

	// 2.���W �\�I ���� �\�I����(1-5)
	@Override
	public FoodMapMeal createfoodMap_meal(String shop, String food, int price, int food_star) {

		// �P�_�s�W�\�I�����a�O�_�s�b
		if (!foodMap_basicDao.existsById(shop)) {
			return null; // return null :��ܩ��a���s�b��basic��shop_basic�̡A�L�k�s�W�\�I
		}

		// �NFoodMapMeal�����D��]��Optional�h�P�_
		FoodMapMealId foodMapMealId = new FoodMapMealId(shop, food);
		Optional<FoodMapMeal> foodMap_mealOp = foodMap_MealDao.findById(foodMapMealId);
		if (foodMap_mealOp.isPresent()) {
			return null; // return null:�����W�P�\�I�W�٤w�s�b
		}

		// �s�J�s���\�I
		FoodMapMeal meal = new FoodMapMeal(shop, food, price, food_star);
		foodMap_MealDao.save(meal);

		List<FoodMapMeal> meallist = foodMap_MealDao.findByShop(shop);

		int total = 0; // total�ө��`�����[�`
		int count = 0; // �p�⦸��
		for (FoodMapMeal meal1 : meallist) {
			total += meal1.getFoodStar(); // total�֥[�p�⦹���a�C���\�I������
			count++; // �p���\�I�������X��
		}

		// �`�����ư����ƪ���(�p��1��)�A�����a����
		double shopStar = (double) total / count; // �]�ܼƪ��total / count(�p���I)
		shopStar = Math.round(shopStar * 10.0) / 10.0;// �|�ˤ��J���ȡA�N�ƭȩw�b�Ȥp�ƲĤ@��

		Optional<FoodMapShop> foodMap_basicOp = foodMap_basicDao.findById(shop); // �M��basic���a
		foodMap_basicOp.get().setShopStar(shopStar);
		foodMap_basicDao.save(foodMap_basicOp.get()); // foodMap_basicDao.save�^�ǭȬ�foodMap_basicOp.get()
		return meal;
	}

	// 3.��s ���� ���W(pk)
	@Override
	public FoodMapShop updateShopInfo(String shopName, String city, String newShopName) {

		Optional<FoodMapShop> shopOp = foodMap_basicDao.findById(shopName);
		if (shopOp.isEmpty()) { // �P�_���L�����a�W��foodMap_basicDao.existsById(shopName)
			return null;
		}

		FoodMapShop shop = shopOp.get();
		// �P�_���L�s������
		if (StringUtils.hasText(city)) {
			shop.setCity(city);
		}
		// �P�_���L�s�����a�W��
		if (StringUtils.hasText(newShopName)) {
			foodMap_basicDao.delete(shop);
			shop.setShopName(newShopName);
			/*
			 * �s�ʸ�Ʈw�\�I����A�N������ө��W�٧令�s���ө��W�� MealDao�̪����a���\�I��imealList
			 * MealDao�̪����a���\�I��imealList
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

	// 4.��s �\�I ���� �\�I����(1-5)
	@Override
	public FoodMapMeal updateMealInfo(String shop, String food, String newfoodName, int price, int food_star) {
		FoodMapMealId foodMapMealId = new FoodMapMealId(shop, food);
		Optional<FoodMapMeal> foodOp = foodMap_MealDao.findById(foodMapMealId);
		if (foodOp.isEmpty()) { // �ө��B�\�I���s�b
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

			// �s�ʸ�Ʈw�\�I����A�N������\�I�����令�s���\�I����
			List<FoodMapMeal> meallist = foodMap_MealDao.findByShop(shop); // �M��meal���a
			int total = 0;
			int count = 0;
			for (FoodMapMeal meal1 : meallist) { 
				total += meal1.getFoodStar();  // total�֥[�p�⦹���a�\�I����
				count++;  // �p���\�I�������X�� 
			}

			// �]�ܼ�shopStar���total / count(�p���I)
			double shopStar = (double) (total + food_star) / (count + 1); 
			shopStar = Math.round(shopStar * 10.0) / 10.0;

			Optional<FoodMapShop> foodMap_basicOp = foodMap_basicDao.findById(shop); // �M��basic���a
			foodMap_basicOp.get().setShopStar(shopStar);
			foodMap_basicDao.save(foodMap_basicOp.get());
		}
		return foodMap_MealDao.save(foodmeal);
	}

	// 5.�j�����䩱�a(��ܩ��a�B���a�����B�\�I�B����B�\�I����)
	@Override
	public List<FoodMapRes> findByCity(String city, int displayAmount) {
		List<FoodMapRes> resList = new ArrayList<FoodMapRes>(); // ���A
		if (foodMap_basicDao.findByCity(city).isEmpty()) { // �P�_�O�_��������
			return null;
		}

		List<FoodMapShop> baiscList = foodMap_basicDao.findByCity(city);// �䫰��
		if (displayAmount == 0 || displayAmount > baiscList.size()) { // ��ܵ��ƭ���0��W�L����ܥ���
			displayAmount = baiscList.size();
		}
		baiscList = baiscList.subList(0, displayAmount); // �����ƭȭ�����ܵ���

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
			res.setMealList(eachShopMealList); // �M�䩱�a�è��o�Ҧ��\�I��T
			res.setShop(shop); //
			resList.add(res);
		}
		return resList;
	}

	// 6.�j�����䩱�a(��ܫ����B���W�B�����B�\�I����B�\������)
	@Override
	public List<FoodMapRes> findByShopStarGreaterThanEqual(double shopStar) {
		List<FoodMapRes> resList = new ArrayList<FoodMapRes>();
		List<FoodMapShop> shopList = foodMap_basicDao.findByShopStarGreaterThanEqualOrderByShopStarDesc(shopStar);// �Ω��a�����䩱�a(�Ƨ�)
		List<String> shopNameList = new ArrayList<>();
		for (FoodMapShop shop : shopList) {
			shopNameList.add(shop.getShopName());// �N��쪺���a��JshopNameList
		}

		List<FoodMapMeal> mealList = foodMap_MealDao.findByShopInOrderByFoodStarDesc(shopNameList); // �̩��W����\�I

		for (FoodMapShop shop : shopList) {
			FoodMapRes res = new FoodMapRes();
			List<FoodMapMeal> eachShopMealList = new ArrayList<>();
			for (FoodMapMeal meal : mealList) {
				if (shop.getShopName().equalsIgnoreCase(meal.getShop())) { // ��ۦP���W
					eachShopMealList.add(meal); // �N�C�@���\�I��T��JeachShopMealList
				}
			}
			res.setShop(shop);
			res.setMealList(eachShopMealList);
			resList.add(res);
		}
		return resList;
	}

	// 7.�Ω���+�\���ӷj�M���a
	@Override
	public List<FoodMapRes> findShopStarFoodStar(double shopStar, int food_star) {
		List<FoodMapRes> resList = new ArrayList<FoodMapRes>();
		List<FoodMapShop> shopList = foodMap_basicDao.findByShopStarGreaterThanEqualOrderByShopStarDesc(shopStar);// �䩱�a����
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
				if (shop.getShopName().equalsIgnoreCase(meal.getShop())) { // ��ۦP���W
					eachShopMealList.add(meal); // �N�C�@���\�I��T��JeachShopMealList
				}
			}
			res.setMealList(eachShopMealList);
			res.setShop(shop);
			resList.add(res);
		}
		return resList;
	}

}
