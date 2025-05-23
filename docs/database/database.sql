-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2025 at 02:25 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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

CREATE TABLE `orders` (
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
