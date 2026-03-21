package orderApp.Online.Food.Order.Application.service;



import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import orderApp.Online.Food.Order.Application.entity.Food;
import orderApp.Online.Food.Order.Application.entity.Order;
import orderApp.Online.Food.Order.Application.entity.Restaurant;

public interface RestaurantService {

		Restaurant createRestaurant(Restaurant restaurant);
		
		Restaurant getById(Integer id);
		
//		List<Restaurant> getAllRestaurants();
		
		Page getAllRestaurants(int pageNum,int pageSize,String sortBy);
		
		Restaurant updateRestaurant(Integer id , Restaurant updateRestaurant);
		
		void deleteRestaurant(Integer id);
		
		Restaurant assignFood(Integer restaurantId,Set<Integer> foodId);
		
		List<Food> findFoodByRestaurantId(Integer id);
		
		List<Order> findOrdersByRestaurantId(Integer id);
}
