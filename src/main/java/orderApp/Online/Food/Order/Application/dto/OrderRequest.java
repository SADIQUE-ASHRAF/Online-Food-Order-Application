package orderApp.Online.Food.Order.Application.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

	@NotNull
	private List<OrderItemRequest> orderItem;
	
	@NotNull(message="restaurant ID cannot be null")
	private Integer restaurantId;

}
