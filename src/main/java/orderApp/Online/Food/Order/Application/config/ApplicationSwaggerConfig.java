package orderApp.Online.Food.Order.Application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationSwaggerConfig {

	@Bean
	OpenAPI openApi() {
		//OpenAPI is a class present in swagger
		//we have multiple server . i return list of(server) localhost
		Server localhost = new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Local Environment");
		
		Contact contact = new Contact();
		contact.setEmail("sadiuqeashraf07@gmail.com");
		contact.setName("Sadique");
		
		Info info = new Info().title("Online Food Order Application")
				.version("1.0".con)
		
	}
}
