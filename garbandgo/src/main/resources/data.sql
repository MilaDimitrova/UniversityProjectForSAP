use grab_and_go;

START TRANSACTION;
INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'администратор'),
(2, 'потребител'),
(3, 'куриер'),
(4, 'доставчик'),
(5, 'мениджър');
INSERT INTO `currencies`(`id`, `currency`) VALUES
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