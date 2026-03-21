
package orderApp.Online.Food.Order.Application.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import orderApp.Online.Food.Order.Application.entity.User;

public interface UserService {

	User createUser(User user);
	
	User getUser(Integer id);
	
	List<User> getAllUser();
	
	User updateUser(User user, Integer id);
	
	void deleteUser(Integer id);
	
	String uploadImage(MultipartFile file, Integer id) throws IOException;
	
	byte[] getImage(Integer id);
}
