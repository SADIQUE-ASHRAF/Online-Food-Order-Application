package orderApp.Online.Food.Order.Application.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String description;
	
	private Float price;
	
	@JsonIgnore
	@ManyToMany(mappedBy="food")
	List<Restaurant> restaurants;

}
