package orderApp.Online.Food.Order.Application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import orderApp.Online.Food.Order.Application.dto.ResponseStructure;
import orderApp.Online.Food.Order.Application.entity.User;
import orderApp.Online.Food.Order.Application.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> createUser(@Valid  @RequestBody User user){
		User saved = userService.createUser(user);
		ResponseStructure<User> apiResponse = new ResponseStructure<>();
		apiResponse.setData(saved);
		apiResponse.setMessage("User Added Successfully");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<User>>(apiResponse,HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/{id}/user/uploadImage")
	public ResponseEntity<ResponseStructure<String>> uploadImage(@RequestParam MultipartFile image,
			@PathVariable Integer id) throws IOException{
		
		String response = userService.uploadImage(image, id);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Success");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
			
	}
	
	@GetMapping("/{id}/get")
	public ResponseEntity<ResponseStructure<User>> getUser(@PathVariable Integer id){
		User response = userService.getUser(id);
		ResponseStructure<User> apiRespose = new ResponseStructure<>();
		apiRespose.setData(response);
		apiRespose.setMessage("User is found");
		apiRespose.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(apiRespose,HttpStatus.OK);
	}
	
	 
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser(){
		List<User> response = userService.getAllUser();
		ResponseStructure<List<User>> apiRespose = new ResponseStructure<>();
		apiRespose.setData(response);
		apiRespose.setMessage("Users found");
		apiRespose.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiRespose,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@PathVariable Integer id){
		User response = userService.updateUser(user, id);
		ResponseStructure<User> apiRespose = new ResponseStructure<>();
		apiRespose.setData(response);
		apiRespose.setMessage("User updated");
		apiRespose.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiRespose,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/{id}/user/getImage",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<ResponseStructure<byte[]>> getImage(@PathVariable Integer id){
		byte[] image = userService.getImage(id);
		ResponseStructure<byte[]> apiResponse = new ResponseStructure<>();
		apiResponse.setData(image);
		apiResponse.setMessage("Success");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return  ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.contentType(MediaType.IMAGE_PNG)
				.body(apiResponse);
	}

}
