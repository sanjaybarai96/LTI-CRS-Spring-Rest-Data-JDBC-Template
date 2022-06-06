package com.lt.service;

import org.springframework.http.ResponseEntity;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface ProfessorServiceInterface {

	public JSONArray viewEnrolledStudents(JSONObject jsonObject);
	public ResponseEntity<?> viewCourse(JSONObject userObj);
}
