package com.example.LoginAPI.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	// create user
	public boolean createUser(String username, String password) {
		String selectQuery = "Select count(*) from user where user_name='" + username + "'";
		//System.out.println("selectQuery: " + selectQuery);
		SqlRowSet srowset = jdbctemplate.queryForRowSet(selectQuery);

		int size = 0;
		if (srowset.next()) {
			size = srowset.getInt(1);
		}
		//System.out.println("size: " + size);

		boolean isUserPresent = false;
		if (size > 0) {
			isUserPresent = true;
		} else {
			String query = "INSERT into user (user_name,password) values ('" + username + "','" + password + "')";
			jdbctemplate.execute(query);
		}
		//System.out.println("isUserPresent: "+isUserPresent);
		return isUserPresent;
	}

	// login user
	public boolean loginUser(String username, String password) {

		String selectQuery = "Select count(*) from user where user_name='" + username + "' and password='" + password
				+ "'";
		SqlRowSet srowset = jdbctemplate.queryForRowSet(selectQuery);

		int size = 0;
		if (srowset.next()) {
			size = srowset.getInt(1);
		}
		
		boolean isUpdated = false;
		if (size > 0) {
			long sessionid = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
			String query = "UPDATE user set session_id='" + sessionid + "' where user_name='" + username
					+ "'and password='" + password + "'";
			jdbctemplate.execute(query);
			isUpdated = true;
		}
		return isUpdated;

	}

	// logout user
	public void logoutUser(String username) {

		String query = "UPDATE user set session_id=0 where user_name='" + username + "'";
		jdbctemplate.execute(query);
	}

	// edit user
	public void editUser(String username, String password) {

		String query = "UPDATE user set password='" + password + "' where user_name='" + username + "'";
		jdbctemplate.execute(query);
	}
}
