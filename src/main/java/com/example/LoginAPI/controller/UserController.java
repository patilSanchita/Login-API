package com.example.LoginAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginAPI.dao.UserDao;
import com.example.LoginAPI.entities.Users;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	// create user
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes=MediaType.ALL_VALUE)
	public Users addUser(@RequestBody Users user) {
//		System.out.println("UserDetails: " + user);
		boolean isUserPresent = userDao.createUser(user.getUsername(), user.getPassword());
		
		if (!isUserPresent) {
			return user;
		} else {
			return null;
		}
	}

	// login user
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes=MediaType.ALL_VALUE)
	public Users loginUser(@RequestBody Users user) {
//		System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword());
		boolean isUpdated = userDao.loginUser(user.getUsername(), user.getPassword());
		if (isUpdated) {
			return user;
		} else {
			return null;
		}
	}

	// logout user
	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes=MediaType.ALL_VALUE)
	public Users logoutUser(@RequestBody Users user) {
		System.out.println("Username: " + user.getUsername());
		userDao.logoutUser(user.getUsername());
		return user;
	}

	// edit user
	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes=MediaType.ALL_VALUE)
	public Users editUser(@RequestBody Users user) {
		System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword());
		userDao.editUser(user.getUsername(), user.getPassword());
		return user;
	}

}
