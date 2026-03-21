package orderApp.Online.Food.Order.Application.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import orderApp.Online.Food.Order.Application.dto.BillResponse;
import orderApp.Online.Food.Order.Application.dto.OrderItemRequest;
import orderApp.Online.Food.Order.Application.dto.OrderRequest;
import orderApp.Online.Food.Order.Application.dto.PaymentDto;
import orderApp.Online.Food.Order.Application.entity.Food;
import orderApp.Online.Food.Order.Application.entity.Order;
import orderApp.Online.Food.Order.Application.entity.OrderItem;
import orderApp.Online.Food.Order.Application.entity.OrderStatus;
import orderApp.Online.Food.Order.Application.entity.Restaurant;
import orderApp.Online.Food.Order.Application.entity.User;
import orderApp.Online.Food.Order.Application.exception.PaymentFailedException;
import orderApp.Online.Food.Order.Application.repository.OrderRepository;
import orderApp.Online.Food.Order.Application.service.FoodService;
import orderApp.Online.Food.Order.Application.service.OrderService;
import orderApp.Online.Food.Order.Application.service.RestaurantService;
import orderApp.Online.Food.Order.Application.service.UserService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final RestaurantService restaurantService;
	private final FoodService foodService;
	private final OrderRepository orderRepository;
	private final UserService userService;

	@Override
	public BillResponse generateBill(OrderRequest orderRequest) {
		Restaurant restaurant = restaurantService.getById(orderRequest.getRestaurantId());
		StringBuilder summary = new StringBuilder();
		
		float totalPrice=0;
		
		for(OrderItemRequest orderItem : orderRequest.getOrderItem()) {
			Food food = foodService.getFoodById(orderItem.getFoodId());
			float price = food.getPrice() * orderItem.getQuantity();
			totalPrice+=price;
			//Idli X 3 = 150
			summary.append(food.getName()).append(" X ").append(orderItem.getQuantity())
				.append(" = ").append(price).append("\n");
		}
		
		return new BillResponse(restaurant.getName(),summary.toString(),totalPrice);
	}

	@Override
	public String payAndPlaceOrder(PaymentDto payment) {
		//simulate payment
		if(payment.isPaymentSuccessful()) {
			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			Restaurant restaurant = restaurantService.getById(payment.getRestaurantId());
			//set restaurant to order
			order.setRestaurant(restaurant);
			
			//set user to order
			User user = userService.getUser(payment.getUserId());
			order.setUser(user);
			
			List<OrderItem> items = new ArrayList();
			double totalPrice=0;
			
			for(OrderItemRequest request : payment.getOrderItems()) {
				Food food = foodService.getFoodById(request.getFoodId());
				
				OrderItem orderItem = new OrderItem();
				orderItem.setFood(food);
				orderItem.setQuantity(request.getQuantity());
				orderItem.setOrder(order);
				
				items.add(orderItem);
				
				double price = food.getPrice() * request.getQuantity();
				totalPrice += price;
			}
			
			order.setTotalPrice(totalPrice);
			order.setOrderItem(items);
			orderRepository.save(order);
			return "Order has been placed";
		}
		else {
			throw new PaymentFailedException("Payment was not successful, hence order cannot be placed");
		}
	}

	@Override
	public void deleteOrder(Integer id) {
		Order order = getOrder(id);
		orderRepository.delete(order);
		
	}

	@Override
	public Order getOrder(Integer id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			return order.get();
		}
		throw new NoSuchElementException("Order with ID: "+id+"does not exists");
	}

	@Override
	public Order updateStatusByAdmin(OrderStatus status, Integer id) {
		 Order order = getOrder(id);
		 order.setStatus(status);
		return orderRepository.save(order);
	}

	@Override
	public String cancelOrder(Integer id) {
		 Order order = getOrder(id);
		 order.setStatus(OrderStatus.CANCELLED);
		 orderRepository.save(order);
		return "Order Cancelled, your money will be refunded in 2 business hours";
	}
	
	
}