package com.lt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lt.consants.Consonant;
import com.lt.dao.StudentDAOImpl;
import com.lt.dto.RegisterCourse;
import com.lt.dto.Student;

import net.minidev.json.JSONObject;

@Service
public class StudentService implements StudentServiceInterface {

	private Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	StudentDAOImpl stduentDao;

	/**
	 * course registration method
	 * 
	 * @param jsonBody
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> courseRegistration(JSONObject jsonBody) {
		try {
			logger.info("Body request :: " + jsonBody);
			long userId = Long.valueOf(jsonBody.get(Consonant.User_id).toString());
			RegisterCourse registerCourse = new RegisterCourse();
			registerCourse.setStudentId(userId);
			registerCourse.setBranch(jsonBody.getAsString(Consonant.Branch_Name));
			if (stduentDao.saveCourseRegistration(registerCourse) != 0) {
				logger.info("coure resgistered saved");
				updateStudent(userId, registerCourse.getBranch());
			}
			return new ResponseEntity<>("Course resgistered succeesfull", HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception occured in courseRegistration:: " + e.getMessage());
			return new ResponseEntity<>("Contact administrator", HttpStatus.CONFLICT);
		}
	}
	
	private void updateStudent(long userId, String branch) {
		Student student = stduentDao.getStudentByID(userId);
		student.setBranch(branch);
		stduentDao.updateStudent(student, userId);
	}

}
