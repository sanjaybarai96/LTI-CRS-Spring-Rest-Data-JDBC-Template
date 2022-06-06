package com.lt.service;

import org.springframework.http.ResponseEntity;

import com.lt.dto.User;

import net.minidev.json.JSONObject;

public interface UserServiceInterface {

	public ResponseEntity<?> userLogin(JSONObject jsonBody);

	public ResponseEntity<?> userLogout(JSONObject jsonBody);

	public ResponseEntity<?> registerUser(JSONObject jsonBody);

	public ResponseEntity<?> resetPassword();

	public ResponseEntity<?> updatePassword(JSONObject jsonBody);

}
