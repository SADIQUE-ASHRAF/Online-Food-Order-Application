package orderApp.Online.Food.Order.Application.serviceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import orderApp.Online.Food.Order.Application.entity.Food;
import orderApp.Online.Food.Order.Application.entity.Order;
import orderApp.Online.Food.Order.Application.entity.Restaurant;
import orderApp.Online.Food.Order.Application.repository.FoodRepository;
import orderApp.Online.Food.Order.Application.repository.RestaurantRepository;
import orderApp.Online.Food.Order.Application.service.RestaurantService;


@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant getById(Integer id) {
		Optional<Restaurant> response = restaurantRepository.findById(id);

		if (response.isPresent()) {
			return response.get();
		} else {
			throw new NoSuchElementException("Restaurant with ID:" + id + " " + "not found");
		}
		// return restaurantRepository.findById(id).orElseThrow(()-> new
		// NoSuchElementException("Restaurant with ID:"+id+"not found")); it is used for
		// industry level java8 concept
	}

	@Override
	public Page getAllRestaurants(int pageNum, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNum, pageSize,sort);
		Page page = restaurantRepository.findAll(pageable);
		
		return page;
	}
	
//	@Override
//	public List<Restaurant> getAllRestaurants() {
//		  List<Restaurant> restaurants = restaurantRepository.findAll();
//		  if(restaurants.isEmpty()) {
//			  return null;
//		  }else {
//			  return restaurants;
//		  }
//	}

	@Override
	public Restaurant updateRestaurant(Integer id, Restaurant updatedRestaurant) {
		Restaurant existing = getById(id);
		existing.setAddress(updatedRestaurant.getAddress());
		existing.setContactNumber(updatedRestaurant.getContactNumber());
		existing.setEmail(updatedRestaurant.getEmail());
		existing.setName(updatedRestaurant.getName());
		
		return restaurantRepository.save(existing);
	}

	@Override
	public void deleteRestaurant(Integer id) {
		Restaurant restaurant = getById(id);
		restaurantRepository.delete(restaurant);
	}

	@Override
	public Restaurant assignFood(Integer restaurantId, Set<Integer> foodId) {
		Restaurant restaurant = getById(restaurantId);
		
		List<Food> foodItems = new ArrayList();
		
		for(Integer id : foodId) {
			Food food = foodRepository.findById(id).orElseThrow(()->new NoSuchElementException("Food with ID: "+id+" not found"));
			foodItems.add(food);
		}
		
		restaurant.setFood(foodItems);
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public List<Food> findFoodByRestaurantId(Integer id) {
	 List<Food> food = restaurantRepository.findFoodByRestaurantId(id);
	 if(food==null || food.size()==0) {
		 throw new NoSuchElementException("Restaurant with ID: "+id+"not found or the food is not"+"assigned to restaurant");
	 }else {
		 return food;
	 }
  }

	@Override
	public List<Order> findOrdersByRestaurantId(Integer id) {
		List<Order> orders = restaurantRepository.findOrderByRestaurantId(id);
		if(orders==null || orders.size()==0) {
			throw new NoSuchElementException("Restaurant with ID:"+id+"not found or there are no"+"orders");
		}else {
			return orders;
		}
		
	}



}
