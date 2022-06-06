package com.lt.service;

import java.time.LocalDate;
import java.util.Date;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lt.consants.Consonant;
import com.lt.consants.Role;
import com.lt.dao.UserDao;
import com.lt.dto.Student;
import com.lt.dto.User;


/**
 * @author user217
 * 
 */
@Service
public class UserService implements UserServiceInterface {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDao userSrevice;

	@Autowired
	StudentServiceInterface studentService;

	@Autowired

	/**
	 * UserLogin method
	 */
	@Override
	public ResponseEntity<?> userLogin(JSONObject jsonBody) {
		try {
			logger.info("Body request :: " + jsonBody);
			String userName = (String) (jsonBody.get(Consonant.User_Name));
			String password = (String) (jsonBody.get(Consonant.Password));

			User user = userSrevice.getUserByUserName(userName);
			if (checkingCredentials(user, userName, password)) {
				user.setSession(true);
				userSrevice.updateSession(user.getUserId(), true);
			}

			return new ResponseEntity<>(user, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception User not Found the Database:: " + e.getMessage());
			return new ResponseEntity<>("User Not found", HttpStatus.CONFLICT);
		}
	}

	private boolean checkingCredentials(User user, String username, String password) {
		if (user != null) {
			if (user.getPassword().equals(password))
				return true;
			else {
				System.out.println("Password does not match");
				return false;
			}
		} else {
			System.out.println("User not found");
			return false;
		}
	}

	@Override
	public ResponseEntity<?> userLogout(JSONObject jsonBody) {

		long userId = Long.valueOf(jsonBody.get(Consonant.User_id).toString());
		userSrevice.updateSession(userId, false);
		return new ResponseEntity<>("Logout successFully", HttpStatus.OK);
	}

	/**
	 * Reset The password
	 */
	@Override
	public ResponseEntity<?> resetPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * update password
	 */
	@Override
	public ResponseEntity<?> updatePassword(JSONObject jsonBody) {
		long userId = Long.valueOf(jsonBody.get(Consonant.User_id).toString());
		String newPassword = jsonBody.get(Consonant.New_Password).toString();

		userSrevice.updateUserPassword(userId, newPassword);
		return new ResponseEntity<>("SuccessFully update Password the User", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> registerUser(User user) {
		
		createUser(user, 0, Role.Student);
	
		//userSrevice.saveUser(user);
		addStudent(user);

		return new ResponseEntity<>("SuccessFully Register the User", HttpStatus.OK);
	}

	/**
	 * @param user
	 * @param isApprove
	 * @param role
	 */
	public void createUser(User user, int isApprove, Role role) {
		user.setUserName(user.getEmailId());
		user.setCreateDate(new Date());
		user.setIsApprove(isApprove);
		user.setRole(role.name());
		user.setSession(false);
		userSrevice.saveUser(user);

	}

	private void addStudent(User user) {
		Student student = new Student();
		student.setStudentId(user.getUserId());
		studentService.addStudent(student);
	}
}
