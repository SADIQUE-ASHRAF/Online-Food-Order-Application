package orderApp.Online.Food.Order.Application.exception;

public class PaymentFailedException extends RuntimeException{
	
	public PaymentFailedException(String message) {
		super(message);
	}

}