package orderApp.Online.Food.Order.Application.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import orderApp.Online.Food.Order.Application.dto.ResponseStructure;
import orderApp.Online.Food.Order.Application.entity.Food;
import orderApp.Online.Food.Order.Application.entity.Order;
import orderApp.Online.Food.Order.Application.entity.Restaurant;
import orderApp.Online.Food.Order.Application.service.RestaurantService;

@RestController
@RequestMapping("/restaurant/api")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Restaurant>> createRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant response = restaurantService.createRestaurant(restaurant);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant Object Created Successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		// integer value:CREATED returns 201 HttpStatus is a Enum
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	// @GetMapping("/get/{ID}") if i give different id means name change i will use
	// with @PathVariable(name="ID")
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> getById(@PathVariable Integer id) {

		Restaurant response = restaurantService.getById(id);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant Object Found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		// OK 200 Object Found
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);

	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page>> getAllRestaurants(
			@RequestParam(defaultValue = "0", required = false) int pageNum,
			@RequestParam(defaultValue = "5", required = false) int pageSize,
			@RequestParam(defaultValue = "createdAt", required = false) String sortBy) {
		Page restaurant = restaurantService.getAllRestaurants(pageNum, pageSize, sortBy);
		ResponseStructure<Page> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurant);
		apiResponse.setMessage("Api ran successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> updateRestaurant(@PathVariable Integer id , @RequestBody Restaurant updateRestaurant){
	   Restaurant updated = restaurantService.updateRestaurant(id, updateRestaurant);
	   ResponseStructure<Restaurant> apiResponse = new ResponseStructure<Restaurant>();
	   apiResponse.setData(updated);
	   apiResponse.setMessage("Restaurant object updated");
	   apiResponse.setStatusCode(HttpStatus.OK.value());
	   
	   return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteById(@PathVariable Integer id) {
		restaurantService.deleteRestaurant(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/{restaurantId}/assignFood")
	public ResponseEntity<ResponseStructure<Restaurant>> assignFood(@PathVariable Integer restaurantId, @RequestBody Set<Integer> food){
		Restaurant restaurant = restaurantService.assignFood(restaurantId, food);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurant);
		apiResponse.setMessage("Assigned");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}/getAll")
	public ResponseEntity<ResponseStructure<List<Food>>> getFoodByRestaurant(@PathVariable Integer id){
		ResponseStructure<List<Food>> apiResponse = new ResponseStructure<List<Food>>();
		apiResponse.setData(restaurantService.findFoodByRestaurantId(id));
		apiResponse.setMessage("Food Items Found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
		
	}
	
	@GetMapping("/{id}/getAllOrders")
	public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByRestaurant(@PathVariable Integer id){
		ResponseStructure<List<Order>> apiResponse = new ResponseStructure<List<Order>>();
		apiResponse.setData(restaurantService.findOrdersByRestaurantId(id));
		apiResponse.setMessage("Orders Found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	

}
