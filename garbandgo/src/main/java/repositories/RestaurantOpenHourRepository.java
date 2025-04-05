package repositories;

import entities.RestaurantOpenHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOpenHourRepository extends JpaRepository<RestaurantOpenHour, Integer> {
}