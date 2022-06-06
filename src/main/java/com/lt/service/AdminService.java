package com.lt.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lt.dto.User;

@Service
public class AdminService implements AdminServiceInterface {

	public List<User> getStudentList() {
//		return userDao.getAllStudentUser();
		return null;
	}

	public void approveStudents(long userId) {
//		return null;
	}

}
