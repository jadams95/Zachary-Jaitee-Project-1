package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.UserDAO;
import com.revature.models.User;

import exceptions.ConflictingUserException;

public class UserService {
	
	private UserDAO userDao = new UserDAO();
	
	public User getByCredentials(String username, String password) {
		if(!username.equals("") && !password.equals("")) {
			return userDao.getByCredetials(username, password);
		}
		else
			return null;
	}
	
	public ArrayList<User> getAllUsers(){
		return userDao.getAll();
	}
	
	public User getById(int userId) {
		if(userId <= 0)
			return null;
		return userDao.getById(userId);
	}
	
	public User add(User newUser) throws ConflictingUserException{
		ArrayList<User> userList = userDao.getAll();
		
		for(User u : userList) {
			if(newUser.getUsername().equals(u.getUsername()) || newUser.getEmail().equals(u.getEmail())) {
				throw new ConflictingUserException("Username or Email already taken");
			}
		}
		return userDao.add(newUser);
	}
	
	// UserDAO.update() is unimplemented
	public ArrayList<User> update(User UpdatedUser){
		return userDao.update(UpdatedUser); // will currently always return null
	}
	
	// UserDAO.delete is unimplemented
	public boolean delete(int userId) {
		return userDao.delete(userId); // will currently always return false
	}

}
