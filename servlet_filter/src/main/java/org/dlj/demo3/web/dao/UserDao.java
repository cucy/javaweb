package org.dlj.demo3.web.dao;

import org.dlj.demo3.web.entity.User;

public class UserDao {
	public User find(String userName, String password) {
		return new User(userName, password);
	}
	
	public User find(String userName) {
		return new User(userName, "123");
	}
}
