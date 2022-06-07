package com.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.dto.User;

@Service
public class AdminService implements AdminServiceInterface {

	@Autowired
	UserService userService;
	
	@Autowired
	ProfessorService professorService;

	public List<Map<String, Object>> getStudentList() {
		return userService.getAllStudentUser();
	}

	public long approveStudents(long userId) {
		return userService.approveStudent(userId);
	}

	public void addProfessor(User user) {
		professorService.addProfessor(user);
	}

}
