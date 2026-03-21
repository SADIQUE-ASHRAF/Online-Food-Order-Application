package orderApp.Online.Food.Order.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;



@Data
@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private int quantity;
	
	@ManyToOne
	private Food food;
	
	@ManyToOne
	@JsonIgnore
	private Order order;

}