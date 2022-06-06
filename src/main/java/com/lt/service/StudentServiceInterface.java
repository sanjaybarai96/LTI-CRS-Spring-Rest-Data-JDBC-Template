package com.lt.service;

import org.springframework.http.ResponseEntity;

import com.lt.dto.Student;

import net.minidev.json.JSONObject;

public interface StudentServiceInterface {

	public ResponseEntity<?> courseRegistration(JSONObject jsonBody);
	
	public ResponseEntity<?> getCourses();
	
	public ResponseEntity<?> addCourse(JSONObject jsonBody);
	
	public ResponseEntity<?> dropCourse(JSONObject jsonBody);
	
	public  ResponseEntity<?> addStudent(Student student);
}
