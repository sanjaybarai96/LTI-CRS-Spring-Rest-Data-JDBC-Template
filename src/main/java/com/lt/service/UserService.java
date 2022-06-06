package com.lt.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

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

import net.minidev.json.JSONObject;


/**
 * @author user217
  *  
 */
@Service
public class UserService implements UserServiceInterface{
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	  UserDao userdao;
	
	@Autowired
	StudentServiceInterface studentService;
	
	@Autowired
	
	/**
	 *UserLogin method 
	 */
	@Override
	public ResponseEntity<?> userLogin(JSONObject jsonBody) {
		try {
			logger.info("Body request :: " + jsonBody);
			String  userName = (String) (jsonBody.get(Consonant.User_Name));
			String password=(String) (jsonBody.get(Consonant.Password));
			
			
			User user=userdao.getUserByUserName(userName);
			if(checkingCredentials(user,userName,password)) {
			user.setSession(true);
				userdao.updateSession(user.getUserId(),true);
			}
			
			return new ResponseEntity<>(user, HttpStatus.OK);
		
		}
		catch(Exception e) {
			logger.error("Exception User not Found the Database:: " + e.getMessage());
			return new ResponseEntity<>("User Not found", HttpStatus.CONFLICT);
		}
	}

	private boolean checkingCredentials(User user,String username, String password) {
		if(user!=null) {
			if(user.getPassword().equals(password))
				return true;
			else {
				System.out.println("Password does not match");
				return false;
			}
		}else {
			System.out.println("User not found");
			return false;
		}
	}
	

	
	@Override
	public ResponseEntity<?> userLogout(JSONObject jsonBody) {
		
		long userId= Long.valueOf(jsonBody.getAsString(Consonant.User_id));
		userdao.updateSession(userId, false);
		return new ResponseEntity<>("Logout successFully", HttpStatus.OK);
	}

	/**
	 *Reset The password
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
		long userId= Long.valueOf(jsonBody.getAsString(Consonant.User_id));
		String newPassword=jsonBody.getAsString(Consonant.New_Password);
		
		userdao.updateUserPassword(userId,newPassword);
		return new ResponseEntity<>("SuccessFully update Password the User", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> registerUser(JSONObject jsonBody) {
		User user  = new User();
		createUser(user,0,Role.Student);
		userdao.saveUser(user);
		addStudent(user);
		
		return new ResponseEntity<>("SuccessFully Register the User", HttpStatus.OK);
	}

	
	/**
	 * @param user
	 * @param isApprove
	 * @param role
	 */
	public void createUser(User user,int isApprove,Role role) {
		user.setCreateDate(LocalDate.now());
		user.setIsApprove(isApprove);
		user.setRole(role.name());
		user.setSession(false);
		userdao.saveUser(user);

}


	private void  addStudent(User user) {
		Student student  = new Student();
		student.setStudentId(user.getUserId());
		studentService.addStudent(student);
	}
}
