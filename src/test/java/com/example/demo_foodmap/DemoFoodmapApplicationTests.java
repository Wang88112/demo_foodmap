package com.example.demo_foodmap;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo_foodmap.entity.FoodMapShop;
import com.example.demo_foodmap.repository.FoodMap_basicDao;

@SpringBootTest(classes = DemoFoodmapApplication.class)
class DemoFoodmapApplicationTests {

//	@Autowired
//	private FoodMap_MealDao foodMap_MealDao;

	@Autowired
	private FoodMap_basicDao foodMap_basicDao;

	@Test
	public void findByShopTest() {

//		List<FoodMapMeal> mealList = foodMap_MealDao.findByShop("AAA");
//		System.out.println(mealList);

		List<FoodMapShop> shopStarList = foodMap_basicDao.findByShopStarGreaterThanEqualOrderByShopStarDesc(3.0);
		for (FoodMapShop shop : shopStarList) {
			System.out.printf("商店名稱 :%s  城市:%s  店家評價:%f \n", shop.getShopName(), shop.getCity(), shop.getShopStar());
		}
//		List<FoodMapMeal> mealList = foodMap_MealDao.findByShopAndFoodStarGreaterThanEqualOrderByFoodStarDesc("CCC",3);		
//		for(FoodMapMeal meal : mealList) {
//			System.out.printf("商店名稱:%s  餐點:%s  價格:%d   餐點評價:%d \n",meal.getShop(),meal.getFood(),meal.getPrice(),meal.getFoodStar());
//		}
	}

	@Test
	public void findByCityTest() {

		List<FoodMapShop> findCity = foodMap_basicDao.findByCity("Tainan");
		System.out.println(findCity);
	}

	@Test
	public void updateShopInfoTest() {
		Optional<FoodMapShop> shopOp = foodMap_basicDao.findById("AAA");
		FoodMapShop shop = shopOp.get();
		shop.setShopName("qqq");
		foodMap_basicDao.delete(shop);
	}

//	@Test
//	public void findByShopGreaterThanEqualOrderByShopStarDesc() {
//		List<FoodMapShop> shopStarList = foodMap_basicDao.findByShopGreaterThanEqualOrderByShopStarDesc(3);
//		List<Double> star = new ArrayList<>();
//		for(FoodMapShop findItem :shopStarList ) {
//			star.add(findItem.getShopStar());
//		}
//		
//		System.out.println(star);
//	}

}
