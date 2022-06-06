package com.lt.service;

import java.util.List;

import com.lt.dto.User;

public interface AdminServiceInterface {

	
	public List<User> getStudentList();
	public void approveStudents(long userId);
	
}
