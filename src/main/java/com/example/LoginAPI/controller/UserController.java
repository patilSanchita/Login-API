package com.example.LoginAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginAPI.dao.UserDao;
import com.example.LoginAPI.entities.Users;
import com.example.LoginAPI.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	private UserService userService;


	// create user
//	@PostMapping("/create")
//	public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
//		System.out.println("Username: " + username + " Password: " + password);
//		//boolean isUserPresent = userDao.createUser(username, password);
////		System.out.println("test val: "+isUserPresent);
////		if (!isUserPresent) {
////			System.out.println("inside if: ");
////			return ResponseEntity.ok("User Registration Successful");
////		} else {
////			System.out.println("inside else: ");
////			return ResponseEntity.ok("User Already Exists");
////		}
//	}
	
	@PostMapping("/createUser")
	public Users addUser(@RequestBody Users user) {
		System.out.println("Username: " + user);
//		boolean isUserPresent = userDao.createUser(username, password);
//		System.out.println("test val: "+isUserPresent);
//		if (!isUserPresent) {
//			System.out.println("inside if: ");
//			return ResponseEntity.ok("User Registration Successful");
//		} else {
//			System.out.println("inside else: ");
//			return ResponseEntity.ok("User Already Exists");
//		}
		return this.userService.addUser(user);
	}

	// login user
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
		System.out.println("Username: " + username + " Password: " + password);
		boolean isUpdated = userDao.loginUser(username, password);
		if (isUpdated) {
			return ResponseEntity.ok("User login successful");
		} else {
			return ResponseEntity.ok("Incorrect username or password");
		}
	}

	// logout user
	@PostMapping("/logout")
	public ResponseEntity<String> logoutUser(@RequestParam String username, @RequestParam String sessionid) {
		System.out.println("Username: " + username + " sessionid: " + sessionid);
		userDao.logoutUser(username);
		return ResponseEntity.ok("Username: " + username + " sessionid: " + sessionid);
	}

	// edit user
	@PostMapping("/edit")
	public ResponseEntity<String> editUser(@RequestParam String username, @RequestParam String password,
			@RequestParam String sessionid) {
		System.out.println("Username: " + username + " Password: " + password + " sessionid: " + sessionid);
		userDao.editUser(username, password);
		return ResponseEntity.ok("Username: " + username + " Password: " + password + " sessionid: " + sessionid);
	}
}
