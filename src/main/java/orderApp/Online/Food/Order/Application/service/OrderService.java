package orderApp.Online.Food.Order.Application.service;

import orderApp.Online.Food.Order.Application.dto.BillResponse;
import orderApp.Online.Food.Order.Application.dto.OrderRequest;
import orderApp.Online.Food.Order.Application.dto.PaymentDto;
import orderApp.Online.Food.Order.Application.entity.Order;
import orderApp.Online.Food.Order.Application.entity.OrderStatus;

public interface OrderService {

	BillResponse generateBill(OrderRequest orderRequest);
	
	String payAndPlaceOrder(PaymentDto payment);
	
	void deleteOrder(Integer id);
	
	Order getOrder(Integer id);
	
	Order updateStatusByAdmin(OrderStatus status,Integer id);
	
	String cancelOrder(Integer id);
}
