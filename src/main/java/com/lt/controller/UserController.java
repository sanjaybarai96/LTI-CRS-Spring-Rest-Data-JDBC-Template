package com.lt.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lt.dto.User;
import com.lt.service.UserServiceInterface;



/**
 * @author user217
 * UserControler for the user Registeration of the user
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired 
	UserServiceInterface userService;
	
	
	/**
	 * @param jsonBody
	 * @return
	 */
	@RequestMapping(value="/userReg",method = RequestMethod.POST)
	@ResponseStatus
	public ResponseEntity<?> userRegistration(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	
	/**
	 * @param jsonBody
	 * @return
	 */
	@RequestMapping(value="/updatePassword",method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@RequestBody JSONObject jsonBody) {
		return userService.updatePassword(jsonBody);
		
	}
	/**
	 * @param jsonBody
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
		public ResponseEntity<?> login(@RequestBody JSONObject jsonBody) {	
		return userService.userLogin(jsonBody);
		
	}
	
	/**
	 * @param jsonBody
	 * @return
	 */
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public ResponseEntity<?> logout(@RequestBody JSONObject jsonBody) {	
	return userService.userLogout(jsonBody);
	}
	
	
}
	

