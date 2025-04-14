create database glovo;
use glovo;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `glovo_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `address` varchar(150) NOT NULL,
  `town` int(11) NOT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cancelled_orders`
--

CREATE TABLE `cancelled_orders` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `canceled_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `reason` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` int(11) NOT NULL,
  `country` varchar(100) NOT NULL,
  `currency` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `currencies`
--

CREATE TABLE `currencies` (
  `id` int(11) NOT NULL,
  `currency` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `discounts`
--

CREATE TABLE `discounts` (
  `id` int(11) NOT NULL,
  `discount` float(2,2) NOT NULL,
  `valid_from` datetime NOT NULL,
  `valid_to` datetime NOT NULL,
  `restautant` int(11) NOT NULL,
  `discounted_category` int(11) DEFAULT NULL,
  `discounted_product` int(11) DEFAULT NULL,
  `added_by` int(11) NOT NULL,
  `added_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(11) NOT NULL,
  `ingredient` varchar(255) NOT NULL,
  `alergen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders`(
  `id` int(11) NOT NULL,
  `restaurant` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `address` int(11) NOT NULL,
  `promocode` int(11) NOT NULL,
  `additional_discount` float(2,2) NOT NULL,
  `total_price` float(4,2) NOT NULL,
  `ordered_at` datetime NOT NULL,
  `due_to_delivery` datetime NOT NULL,
  `packed_at` datetime DEFAULT NULL,
  `packed_by` int(11) NOT NULL,
  `delivered_at` datetime DEFAULT NULL,
  `delivered_by` int(11) NOT NULL,
  `cancelled_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_products`
--

CREATE TABLE `order_products` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  `qunatity` int(5) NOT NULL,
  `price` float(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `product` varchar(255) NOT NULL,
  `category` int(11) NOT NULL,
  `image` text NOT NULL,
  `description` text NOT NULL,
  `restaurant` int(11) NOT NULL,
  `delivery_price` float(2,2) NOT NULL,
  `price` float(3,2) NOT NULL,
  `currency` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `id` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_ingredients`
--

CREATE TABLE `product_ingredients` (
  `id` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  `ingredient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promocodes`
--

CREATE TABLE `promocodes` (
  `id` int(11) NOT NULL,
  `promocode` varchar(60) NOT NULL,
  `description` text DEFAULT NULL,
  `restaurant` int(11) NOT NULL,
  `valid_from` datetime NOT NULL,
  `valid_to` datetime NOT NULL,
  `discount` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE `restaurants` (
  `id` int(11) NOT NULL,
  `restaurant` varchar(255) NOT NULL,
  `logo` text NOT NULL,
  `address` int(11) NOT NULL,
  `reputation` float(1,1) DEFAULT NULL,
  `manager` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_open_hours`
--

CREATE TABLE `restaurant_open_hours` (
  `id` int(11) NOT NULL,
  `restaurant` int(11) NOT NULL,
  `opens_at` time DEFAULT NULL,
  `closes_at` time DEFAULT NULL,
  `day_of_week` enum('Понеделник','Вторник','Сряда','Четвъртък','Петък','Събота','Неделя') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `review_text` text NOT NULL,
  `review` int(1) NOT NULL,
  `order_id` int(11) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'администратор'),
(2, 'потребител'),
(3, 'куриер'),
(4, 'доставчик'),
(5, 'мениджър');

-- --------------------------------------------------------

--
-- Table structure for table `towns`
--

CREATE TABLE `towns` (
  `id` int(11) NOT NULL,
  `town` varchar(200) NOT NULL,
  `country` int(11) NOT NULL,
  `zip_code` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(70) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `role` int(11) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`),
  ADD KEY `town` (`town`);

--
-- Indexes for table `cancelled_orders`
--
ALTER TABLE `cancelled_orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `currency` (`currency`);

--
-- Indexes for table `currencies`
--
ALTER TABLE `currencies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discounts`
--
ALTER TABLE `discounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `restautant` (`restautant`),
  ADD KEY `discounted_category` (`discounted_category`),
  ADD KEY `discounted_product` (`discounted_product`),
  ADD KEY `added_by` (`added_by`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `restaurant` (`restaurant`),
  ADD KEY `user` (`user`),
  ADD KEY `packed_by` (`packed_by`),
  ADD KEY `delivered_by` (`delivered_by`),
  ADD KEY `address` (`address`),
  ADD KEY `promocode` (`promocode`),
  ADD KEY `cancelled_id` (`cancelled_id`);

--
-- Indexes for table `order_products`
--
ALTER TABLE `order_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product` (`product`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `currency` (`currency`),
  ADD KEY `restaurant` (`restaurant`),
  ADD KEY `category` (`category`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_ingredients`
--
ALTER TABLE `product_ingredients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ingredient` (`ingredient`),
  ADD KEY `product` (`product`);

--
-- Indexes for table `promocodes`
--
ALTER TABLE `promocodes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `promocode` (`promocode`),
  ADD KEY `restaurant` (`restaurant`);

--
-- Indexes for table `restaurants`
--
ALTER TABLE `restaurants`
  ADD PRIMARY KEY (`id`),
  ADD KEY `address` (`address`),
  ADD KEY `manager` (`manager`);

--
-- Indexes for table `restaurant_open_hours`
--
ALTER TABLE `restaurant_open_hours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `restaurant` (`restaurant`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `towns`
--
ALTER TABLE `towns`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `town` (`town`,`zip_code`),
  ADD KEY `country` (`country`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role` (`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cancelled_orders`
--
ALTER TABLE `cancelled_orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `currencies`
--
ALTER TABLE `currencies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `discounts`
--
ALTER TABLE `discounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_products`
--
ALTER TABLE `order_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_ingredients`
--
ALTER TABLE `product_ingredients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promocodes`
--
ALTER TABLE `promocodes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `restaurants`
--
ALTER TABLE `restaurants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `restaurant_open_hours`
--
ALTER TABLE `restaurant_open_hours`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `towns`
--
ALTER TABLE `towns`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`town`) REFERENCES `towns` (`id`),
  ADD CONSTRAINT `addresses_ibfk_2` FOREIGN KEY (`user`) REFERENCES `users` (`id`);

--
-- Constraints for table `cancelled_orders`
--
ALTER TABLE `cancelled_orders`
  ADD CONSTRAINT `cancelled_orders_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `countries`
--
ALTER TABLE `countries`
  ADD CONSTRAINT `countries_ibfk_1` FOREIGN KEY (`currency`) REFERENCES `currencies` (`id`);

--
-- Constraints for table `discounts`
--
ALTER TABLE `discounts`
  ADD CONSTRAINT `discounts_ibfk_1` FOREIGN KEY (`discounted_category`) REFERENCES `product_category` (`id`),
  ADD CONSTRAINT `discounts_ibfk_2` FOREIGN KEY (`discounted_product`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `discounts_ibfk_3` FOREIGN KEY (`restautant`) REFERENCES `restaurants` (`id`),
  ADD CONSTRAINT `discounts_ibfk_4` FOREIGN KEY (`added_by`) REFERENCES `users` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`delivered_by`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`packed_by`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`restaurant`) REFERENCES `restaurants` (`id`),
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`address`) REFERENCES `addresses` (`id`),
  ADD CONSTRAINT `orders_ibfk_5` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `orders_ibfk_6` FOREIGN KEY (`cancelled_id`) REFERENCES `cancelled_orders` (`id`),
  ADD CONSTRAINT `orders_ibfk_7` FOREIGN KEY (`promocode`) REFERENCES `promocodes` (`id`);

--
-- Constraints for table `order_products`
--
ALTER TABLE `order_products`
  ADD CONSTRAINT `order_products_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_products_ibfk_2` FOREIGN KEY (`product`) REFERENCES `products` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`restaurant`) REFERENCES `restaurants` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category`) REFERENCES `product_category` (`id`);

--
-- Constraints for table `product_ingredients`
--
ALTER TABLE `product_ingredients`
  ADD CONSTRAINT `product_ingredients_ibfk_1` FOREIGN KEY (`ingredient`) REFERENCES `ingredients` (`id`),
  ADD CONSTRAINT `product_ingredients_ibfk_2` FOREIGN KEY (`product`) REFERENCES `products` (`id`);

--
-- Constraints for table `promocodes`
--
ALTER TABLE `promocodes`
  ADD CONSTRAINT `promocodes_ibfk_1` FOREIGN KEY (`restaurant`) REFERENCES `restaurants` (`id`);

--
-- Constraints for table `restaurants`
--
ALTER TABLE `restaurants`
  ADD CONSTRAINT `restaurants_ibfk_1` FOREIGN KEY (`address`) REFERENCES `addresses` (`id`),
  ADD CONSTRAINT `restaurants_ibfk_2` FOREIGN KEY (`manager`) REFERENCES `users` (`id`);

--
-- Constraints for table `restaurant_open_hours`
--
ALTER TABLE `restaurant_open_hours`
  ADD CONSTRAINT `restaurant_open_hours_ibfk_1` FOREIGN KEY (`restaurant`) REFERENCES `restaurants` (`id`);

--
-- Constraints for table `towns`
--
ALTER TABLE `towns`
  ADD CONSTRAINT `towns_ibfk_1` FOREIGN KEY (`country`) REFERENCES `countries` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


START TRANSACTION;

INSERT INTO `currencies` (`id`, `currency`) VALUES
(1, 'BGN'),
(2, 'EUR'),
(3, 'USD');

INSERT INTO `countries` (`id`, `country`, `currency`) VALUES
(1, 'Bulgaria', 1),
(2, 'Germany', 2),
(3, 'United States', 3);

INSERT INTO `towns` (`id`, `town`, `country`, `zip_code`) VALUES
(1, 'Sofia', 1, '1000'),
(2, 'Plovdiv', 1, '4000'),
(3, 'Berlin', 2, '10115'),
(4, 'New York', 3, '10001');

INSERT INTO `users` (`id`, `username`, `email`, `phone`, `role`, `password`) VALUES
(1, 'admin', 'admin@glovoapp.com', '0888123456', 1, '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
(2, 'user1', 'user1@example.com', '0888111111', 2, '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
(3, 'courier1', 'courier1@glovoapp.com', '0888222222', 3, '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
(4, 'manager1', 'manager1@glovoapp.com', '0888333333', 5, '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi'),
(5, 'rest_owner1', 'owner1@restaurant.com', '0888444444', 4, '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi');

INSERT INTO `addresses` (`id`, `address`, `town`, `user`) VALUES
(1, 'bul. Vitosha 12', 1, 2),
(2, 'ul. Gladston 5', 1, NULL),
(3, 'ul. Ivan Vazov 20', 2, NULL),
(4, 'Friedrichstraße 100', 3, NULL),
(5, '5th Avenue 200', 4, NULL);

INSERT INTO `product_category` (`id`, `category`, `image`) VALUES
(1, 'Pizza', 'pizza.jpg'),
(2, 'Burger', 'burger.jpg'),
(3, 'Salad', 'salad.jpg'),
(4, 'Dessert', 'dessert.jpg'),
(5, 'Drink', 'drink.jpg');

INSERT INTO `ingredients` (`id`, `ingredient`, `alergen`) VALUES
(1, 'Flour', 1),
(2, 'Tomato sauce', 0),
(3, 'Mozzarella', 1),
(4, 'Pepperoni', 0),
(5, 'Beef patty', 0),
(6, 'Lettuce', 0),
(7, 'Tomato', 0),
(8, 'Onion', 0),
(9, 'Chicken', 0),
(10, 'Chocolate', 1);

INSERT INTO `restaurants` (`id`, `restaurant`, `logo`, `address`, `reputation`, `manager`) VALUES
(1, 'Pizza Heaven', 'pizza_heaven.jpg', 2, 4.5, 4),
(2, 'Burger King', 'burger_king.jpg', 3, 4.2, 4),
(3, 'Healthy Salads', 'healthy_salads.jpg', 4, 4.7, 5);

INSERT INTO `products` (`id`, `product`, `category`, `image`, `description`, `restaurant`, `delivery_price`, `price`, `currency`) VALUES
(1, 'Margherita Pizza', 1, 'margherita.jpg', 'Classic pizza with tomato sauce and mozzarella', 1, 2.50, 10.99, 1),
(2, 'Pepperoni Pizza', 1, 'pepperoni.jpg', 'Pizza with tomato sauce, mozzarella and pepperoni', 1, 2.50, 12.99, 1),
(3, 'Classic Burger', 2, 'classic_burger.jpg', 'Beef patty with lettuce, tomato and onion', 2, 2.00, 8.99, 1),
(4, 'Chicken Salad', 3, 'chicken_salad.jpg', 'Fresh salad with grilled chicken', 3, 1.50, 9.50, 1),
(5, 'Chocolate Cake', 4, 'chocolate_cake.jpg', 'Delicious chocolate dessert', 1, 1.00, 5.99, 1);

INSERT INTO `product_ingredients` (`id`, `product`, `ingredient`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 1),
(5, 2, 2),
(6, 2, 3),
(7, 2, 4),
(8, 3, 5),
(9, 3, 6),
(10, 3, 7),
(11, 3, 8),
(12, 4, 6),
(13, 4, 7),
(14, 4, 8),
(15, 4, 9),
(16, 5, 10);

INSERT INTO `promocodes` (`id`, `promocode`, `description`, `restaurant`, `valid_from`, `valid_to`, `discount`) VALUES
(1, 'BEIBE20', '20% off all pizzas', 1, '2025-01-01 00:00:00', '2025-12-31 23:59:59', 20),
(2, 'KUMBATA15', '15% off all burgers', 2, '2025-01-01 00:00:00', '2025-06-30 23:59:59', 15),
(3, 'SHISHKOV10', '10% off all salads', 3, '2025-03-01 00:00:00', '2025-03-31 23:59:59', 10);

INSERT INTO `discounts` (`id`, `discount`, `valid_from`, `valid_to`, `restautant`, `discounted_category`, `discounted_product`, `added_by`, `added_at`) VALUES
(1, 0.10, '2025-03-01 00:00:00', '2025-03-07 23:59:59', 1, 1, NULL, 4, '2025-02-25 10:00:00'),
(2, 0.15, '2025-03-15 00:00:00', '2025-03-17 23:59:59', 2, NULL, 3, 4, '2025-03-01 09:30:00'),
(3, 0.20, '2025-04-01 00:00:00', '2025-04-30 23:59:59', 3, 3, NULL, 5, '2025-03-15 14:15:00');

INSERT INTO `restaurant_open_hours` (`id`, `restaurant`, `opens_at`, `closes_at`, `day_of_week`) VALUES
(1, 1, '10:00:00', '22:00:00', 'Понеделник'),
(2, 1, '10:00:00', '22:00:00', 'Вторник'),
(3, 1, '10:00:00', '22:00:00', 'Сряда'),
(4, 1, '10:00:00', '23:00:00', 'Четвъртък'),
(5, 1, '10:00:00', '23:00:00', 'Петък'),
(6, 1, '11:00:00', '23:00:00', 'Събота'),
(7, 1, '11:00:00', '22:00:00', 'Неделя'),
(8, 2, '09:00:00', '21:00:00', 'Понеделник'),
(9, 2, '09:00:00', '21:00:00', 'Вторник'),
(10, 2, '09:00:00', '21:00:00', 'Сряда'),
(11, 2, '09:00:00', '22:00:00', 'Четвъртък'),
(12, 2, '09:00:00', '22:00:00', 'Петък'),
(13, 2, '10:00:00', '22:00:00', 'Събота'),
(14, 2, '10:00:00', '21:00:00', 'Неделя');

INSERT INTO `orders` (`id`, `restaurant`, `user`, `address`, `promocode`, `additional_discount`, `total_price`, `ordered_at`, `due_to_delivery`, `packed_at`, `packed_by`, `delivered_at`, `delivered_by`, `cancelled_id`) 
VALUES 
(1, 1, 2, 1, 1, 0.00, 25.50, '2023-05-15 12:30:00', '2023-05-15 13:00:00', '2023-05-15 12:45:00', 4, '2023-05-15 12:55:00', 3, NULL),
(2, 1, 2, 1, 2, 0.10, 18.75, '2023-05-16 18:15:00', '2023-05-16 18:45:00', '2023-05-16 18:25:00', 4, '2023-05-16 18:40:00', 3, NULL),
(3, 1, 2, 1, 3, 0.00, 32.00, '2023-05-17 19:30:00', '2023-05-17 20:00:00', '2023-05-17 19:45:00', 4, NULL, 3, NULL);

INSERT INTO `order_products` (`order_id`, `product`, `qunatity`, `price`) VALUES
(1, 1, 1, 10.99),
(1, 5, 1, 5.99),
(2, 3, 1, 8.99),
(2, 5, 1, 5.99),
(3, 4, 1, 9.50);

INSERT INTO `reviews` (`id`, `review_text`, `review`, `order_id`, `date_created`) VALUES
(1, 'Great pizza, fast delivery!', 5, 1, '2025-03-01 14:30:00'),
(2, 'Burger was cold when arrived', 3, 2, '2025-03-02 19:45:00');

INSERT INTO `cancelled_orders` (`id`, `order_id`, `canceled_at`, `reason`) VALUES
(1, 3, '2025-03-03 13:40:00', 'Restaurant ran out of ingredients');

UPDATE `orders` SET `cancelled_id` = 1 WHERE `id` = 3;

COMMIT;