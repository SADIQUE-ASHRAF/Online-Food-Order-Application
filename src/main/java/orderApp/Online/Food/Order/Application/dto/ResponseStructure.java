package orderApp.Online.Food.Order.Application.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {
   //T stands for Type Parameter
	private int statusCode;
	private String message;
	private	T data;
}
