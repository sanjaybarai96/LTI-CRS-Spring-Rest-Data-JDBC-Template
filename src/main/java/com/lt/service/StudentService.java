package com.lt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lt.consants.Consonant;
import com.lt.dao.StudentDAOImpl;
import com.lt.dto.Course;
import com.lt.dto.RegisterCourse;
import com.lt.dto.Student;
import com.lt.exception.UserNotFoundException;

import net.minidev.json.JSONObject;

@Service
public class StudentService implements StudentServiceInterface {

	private Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	StudentDAOImpl studentDao;

	@Autowired
	CourseService courseService;

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
			if (studentDao.saveCourseRegistration(registerCourse) != 0) {
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
		Student student = studentDao.getStudentByID(userId);
		student.setBranch(branch);
		studentDao.updateStudent(student, userId);
	}

	/**
	 *Getting all the course list
	 */
	public ResponseEntity<?> getCourses() {
		return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
	}

	/**
	 * Get course belongs to user selected
	 * @param userId
	 * @return
	 */
	public ResponseEntity<?> getCourse(long userId) {
		Student student = studentDao.getStudentByID(userId);
		if (student.getCourseCode() != null && !student.getCourseCode().isEmpty()) {
			List<String> studentCourseCode = Arrays.asList(student.getCourseCode().split(","));
			List<Course> courses = courseService.getCourseByCourseCode(studentCourseCode);
			return new ResponseEntity<>(courses, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Please first add the course", HttpStatus.OK);
		}
	}

	
	/**
	 * Adding the course by student
	 * @param jsonBody
	 */
	public ResponseEntity<?> addCourse(JSONObject jsonBody) {
		long userId = Long.valueOf(jsonBody.getAsString(Consonant.User_id));
		Student student = studentDao.getStudentByID(userId);
		if (student != null) {
			String courseCode = jsonBody.getAsString(Consonant.Course_Code);
			student.setCourseCode((student.getCourseCode() == null || student.getCourseCode().isEmpty()) ? courseCode
					: String.join(",", student.getCourseCode(), courseCode));
			studentDao.updateStudent(student, userId);
		} else {
			return new ResponseEntity<Object>("User not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(student, HttpStatus.OK);
	}

	
	/**
	 *Removing course by student
	 *@param jsonBody
	 */
	public ResponseEntity<?> dropCourse(JSONObject jsonBody) {
		try {
			logger.info("Body request:: " + jsonBody);
			long userId = Long.valueOf(jsonBody.getAsString(Consonant.User_id));

			Student student = studentDao.getStudentByID(userId);
			if (student != null) {
				String courseCode = jsonBody.getAsString(Consonant.Course_Code);
				List<String> stdCourseCodeList = new ArrayList<String>(Arrays.asList(student.getCourseCode().split(",")));
				stdCourseCodeList.removeAll(Arrays.asList(courseCode.split(",")));
				student.setCourseCode(stdCourseCodeList.stream().collect(Collectors.joining(",")));
				studentDao.updateStudent(student, userId);
			} else {
				throw new UserNotFoundException(jsonBody.getAsString(Consonant.User_id));
			}
			return new ResponseEntity<Object>(student, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("Contact administrator", HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("Contact administrator", HttpStatus.CONFLICT);
		}
	}

	/**
	 *add student 
	 */
	@Override
	public ResponseEntity<?> addStudent(Student student) {
	logger.info("Body request:: " );
	try {
	if(student != null) {
		studentDao.saveStudent(student);
		}
	}catch(Exception e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>("Student not saved in the database", HttpStatus.CONFLICT);
	}
	return new ResponseEntity<Object>(student, HttpStatus.OK);		
	}
	
	}

