package org.medmota.restdemo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.medmota.restdemo.entity.User;
import org.medmota.restdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			Map<String, Object> mapResponse = new LinkedHashMap<>();

			userService.save(user);

			mapResponse.put("status", 1);
			mapResponse.put("message", "Record is Saved successfully!!");

			return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		try {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			List<User> listAllUsers = userService.getAllUsers();
			if (!listAllUsers.isEmpty()) {
				mapResponse.put("status", 1);
				mapResponse.put("data", listAllUsers);
				return new ResponseEntity<>(mapResponse, HttpStatus.OK);
			} else {
				mapResponse.clear();
				mapResponse.put("status", 0);
				mapResponse.put("message", "No Data was Founded!");
				return new ResponseEntity<>(mapResponse, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
		try {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			Optional<User> userData = userService.findUserById(id);
			if (userData.isPresent()) {
				mapResponse.put("status", 1);
				mapResponse.put("data", userData.get());
				return new ResponseEntity<>(mapResponse, HttpStatus.OK);
			} else {
				mapResponse.clear();
				mapResponse.put("status", 0);
				mapResponse.put("message", "No Data was Founded!");
				return new ResponseEntity<>(mapResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		try {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			Optional<User> userData = userService.findUserById(id);
			if (userData.isPresent()) {

				User _user = userData.get();
				_user.setUserName(user.getUserName());
				_user.setMobileNo(user.getMobileNo());
				_user.setEmailId(user.getEmailId());
				_user.setPassword(user.getPassword());
				_user.setCity(user.getCity());

				userService.save(_user);

				mapResponse.put("status", 1);
				mapResponse.put("data", _user);
				return new ResponseEntity<>(mapResponse, HttpStatus.OK);
			} else {
				mapResponse.clear();
				mapResponse.put("status", 0);
				mapResponse.put("message", "No Data was Founded!");
				return new ResponseEntity<>(mapResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
		try {
			Map<String, Object> mapResponse = new LinkedHashMap<>();
			Optional<User> userData = userService.findUserById(id);
			if (userData.isPresent()) {
				userService.delete(userData.get());
				mapResponse.put("status", 1);
				mapResponse.put("message", "User deleted successfully!!");
				return new ResponseEntity<>(mapResponse, HttpStatus.OK);
			} else {
				mapResponse.clear();
				mapResponse.put("status", 0);
				mapResponse.put("message", "No Data was Founded!");
				return new ResponseEntity<>(mapResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
