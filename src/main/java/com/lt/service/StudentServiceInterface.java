package com.lt.service;

import org.springframework.http.ResponseEntity;

import net.minidev.json.JSONObject;

public interface StudentServiceInterface {

	public ResponseEntity courseRegistration(JSONObject jsonBody);
}
