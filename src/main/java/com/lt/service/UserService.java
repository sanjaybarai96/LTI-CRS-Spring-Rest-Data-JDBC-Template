package com.lt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lt.consants.Consonant;
import com.lt.consants.Role;
import com.lt.dao.UserDaoImpl;
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
	UserDaoImpl userDao;

	@Autowired
	StudentServiceInterface studentService;

	/**
	 * UserLogin method
	 */
	@Override
	public ResponseEntity<?> userLogin(JSONObject jsonBody) {
		try {
			logger.info("Body request :: " + jsonBody);
			String userName = (String) (jsonBody.get(Consonant.User_Name));
			String password = (String) (jsonBody.get(Consonant.Password));

			Map<String,Object> userMap = userDao.getUserByUserName(userName);
			if (checkingCredentials(userMap, userName, password)) {
				userMap.put("session",true);
				userDao.updateSession(Long.valueOf(userMap.get("userId").toString()), true);
			}

			return new ResponseEntity<>(userMap, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception User not Found the Database:: " + e.getMessage());
			return new ResponseEntity<>("User Not found", HttpStatus.CONFLICT);
		}
	}

	private boolean checkingCredentials(Map<String,Object> userMap, String username, String password) {
		if (userMap != null) {
			if (userMap.get("password").equals(password))
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
		userDao.updateSession(userId, false);
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

		userDao.updateUserPassword(userId, newPassword);
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
		long userId =userDao.saveUser(user);
		user.setUserId(userId);

	}

	private void addStudent(User user) {
		Student student = new Student();
		student.setStudentId(user.getUserId());
		studentService.addStudent(student);
	}

	public List<Map<String,Object>> getAllStudentUser() {
		List<Map<String,Object>> studentUser = userDao.getAllStudentUser();
		return studentUser;
	}

	public long approveStudent(long userId) {
		return userDao.approveStudent(userId);
		
	}
}
