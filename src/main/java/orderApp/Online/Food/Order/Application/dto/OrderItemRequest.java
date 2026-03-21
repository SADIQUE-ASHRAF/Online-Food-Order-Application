package orderApp.Online.Food.Order.Application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemRequest {
	
	@NotBlank
	private Integer foodId;
	
	@Min(1)
	private int quantity;
	

}
