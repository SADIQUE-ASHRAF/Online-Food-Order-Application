package orderApp.Online.Food.Order.Application.dto;

import java.util.List;

import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {
 
	@NotNull
//	@Schema(description = "This field specifies the list of OrderItemRequest objects")
	private List<OrderItemRequest> orderItems;
	
	
	private boolean paymentSuccessful;
	
	@NotNull
	private Integer restaurantId;
	
	@NotNull
	private Integer userId;
}
