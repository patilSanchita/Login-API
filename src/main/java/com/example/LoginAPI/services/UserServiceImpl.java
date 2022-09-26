package com.example.LoginAPI.services;

//whenever we are going to call getCourses(), the Services will going to return below list in which we have added the courses.
//@Autowired //it will automatically object of implementation class CourseDao.java and it will inject the dependencies and it will provide its object into CourseServiceImpl.java

import org.springframework.beans.factory.annotation.Autowired;
import com.example.LoginAPI.dao.UserDao;
import com.example.LoginAPI.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

  @Autowired //it will automatically object and it will inject the dependencies
  private UserDao userDao;

  //get user details
//  @Override
//  public Users getUser(String username) {
//      return userDao.getOne(username); //get single user
//  }
//
  //add the user
  @Override
  public Users addUser(Users user) {
      userDao.createUser(user); //it will save user to the database.
      return user;
  }
//
//  //update user password
//  @Override
//  public Users updatePassword(Users user) {
//      userDao.save(user);
//      return user;
//  }

}