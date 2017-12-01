package com.bridgeit.todo.service;

import com.bridgeit.todo.model.User;

public interface UserService {

	int saveUser(User user);

	//User userLogin(String email, String password);
	User userLogin(User user);

	static void sendMail(String email) {
	}

	User getUserByEmail(String email);

	boolean setPassword(User user1);

	User getUserById(int id);

	boolean updateUser(User user);

	boolean userExists(User cUser);
}
