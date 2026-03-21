package orderApp.Online.Food.Order.Application.entity;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Not Null Pattern and NotBlank comes form spring boot starter validation dependency
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	@NotNull
	private String name;
	
	@NotNull
	@NotBlank
	@Pattern(
			regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			flags = Pattern.Flag.CASE_INSENSITIVE,
			message = "Invalid email format"
			)
	
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
	
	@NotNull
	private long contactNumber;
	
	private String gender;
	
	// Hibernate Annotation
	@Lob 
	private byte[] image;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Order> orders;

}
