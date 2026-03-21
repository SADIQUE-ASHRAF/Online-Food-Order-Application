package orderApp.Online.Food.Order.Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import orderApp.Online.Food.Order.Application.entity.Food;
import orderApp.Online.Food.Order.Application.entity.Order;
import orderApp.Online.Food.Order.Application.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	@Query("SELECT r.food FROM Restaurant r WHERE r.id = :restaurantId")
	List<Food> findFoodByRestaurantId(@Param(value="restaurantId")Integer id);
	
	@Query("SELECT r.orders FROM Restaurant r WHERE r.id = :restaurantId")
	List<Order> findOrderByRestaurantId(@Param(value="restaurantId")Integer id);

}
