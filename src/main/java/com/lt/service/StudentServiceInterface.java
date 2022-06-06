package com.lt.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

import com.lt.dto.Student;


public interface StudentServiceInterface {

	public ResponseEntity<?> courseRegistration(JSONObject jsonBody);

	public ResponseEntity<?> getCourses();

	public ResponseEntity<?> addCourse(JSONObject jsonBody);

	public ResponseEntity<?> dropCourse(JSONObject jsonBody);

	public List<Student> getStudentsByCourseCode(List<String> courseCodes);

	public ResponseEntity<?> addStudent(Student student);
}
