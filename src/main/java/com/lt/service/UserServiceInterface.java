package com.lt.service;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

import com.lt.dto.User;


public interface UserServiceInterface {

	public ResponseEntity<?> userLogin(JSONObject jsonBody);

	public ResponseEntity<?> userLogout(JSONObject jsonBody);

	public ResponseEntity<?> registerUser(User user);

	public ResponseEntity<?> resetPassword();

	public ResponseEntity<?> updatePassword(JSONObject jsonBody);

}
