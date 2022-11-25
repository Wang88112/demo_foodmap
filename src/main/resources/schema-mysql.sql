CREATE TABLE `food_map_basic` (
  `shop_basic` varchar(20) NOT NULL,
  `city_basic` varchar(20) DEFAULT NULL,
  `shop_star` double DEFAULT NULL,
  PRIMARY KEY (`shop_basic`)
);

CREATE TABLE `food_map_meal` (
  `shop_in` varchar(20) NOT NULL,
  `food` varchar(45) NOT NULL,
  `price` int DEFAULT NULL,
  `food_star` int DEFAULT NULL,
  PRIMARY KEY (`shop_in`,`food`)
);