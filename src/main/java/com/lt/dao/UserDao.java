package com.lt.dao;

import java.util.List;
import java.util.Map;

import com.lt.dto.User;

public interface UserDao {

	Map<String, Object> getUserByUserName(String username);

	List<User> getAllUser();

	List<Map<String, Object>> getAllStudentUser();

	long saveUser(User user);

	void updateSession(long userId, boolean session);

	public long updateUserPassword(long userId, String newPassword);

	public long approveStudent(long userId);

}
