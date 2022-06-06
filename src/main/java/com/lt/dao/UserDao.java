package com.lt.dao;



import java.util.List;

import com.lt.dto.User;



public interface UserDao {

	User getUserByUserName(String username);

	List<User> getAllUser();

	List<User> getAllStudentUser();

	long saveUser(User user);

	void updateSession(long userId,boolean session);

	public long updateUserPassword(long userId,String newPassword);

}
