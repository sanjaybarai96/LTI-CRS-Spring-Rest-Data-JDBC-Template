package com.lt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lt.consants.Consonant;
import com.lt.dao.ProfessorDAOImpl;
import com.lt.dto.Course;
import com.lt.dto.Professor;
import com.lt.dto.Student;
import com.lt.dto.User;
import com.lt.exception.UserNotFoundException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class ProfessorService implements ProfessorServiceInterface {

	Logger logger = LoggerFactory.getLogger(ProfessorService.class);
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ProfessorDAOImpl professorDao;
	
	@Autowired
	StudentService studentService;
	
	@Override
	public JSONArray viewEnrolledStudents(JSONObject jsonObject) {
		long userId = Long.valueOf(jsonObject.getAsString(Consonant.User_id));
		Professor professor = professorDao.getProfessorById(userId);
		if(professor == null) {
			throw new UserNotFoundException(jsonObject.getAsString(Consonant.User_id));
		}else {
			String professorName = professor.getName();
			List<Course> courseList =  courseService.getCourseByInstructor(professorName);
			List<Student> students =  studentService.getStudentsByCourseCode(courseList.stream().map(Course::getCourseCode).collect(Collectors.toList()));
			List<User> studentUsers = new ArrayList<>(); 
//					userDao.getUserById(students.stream().map(Student::getStudentId).collect(Collectors.toList()));
			JSONArray jsArray = createResponse(studentUsers,students,courseList);
			return jsArray;
		}
	}

	private JSONArray createResponse(List<User> studentUsers, List<Student> students, List<Course> courseList) {
		JSONArray jsArray = new JSONArray();
		courseList.forEach(course->{
			JSONObject js = new JSONObject();
			List<Student> newStudents = students.stream().filter(std->std.getCourseCode().contains(course.getCourseCode())).collect(Collectors.toList());
			newStudents.forEach(std->{
				User user =studentUsers.stream().filter(usr->usr.getUserId() == std.getStudentId()).findAny().get();
				js.put("userId", user.getUserId());
				js.put("firstName",user.getFirstname());
				js.put("firstName",user.getLastName());
				js.put("emailId",user.getEmailId());
				js.put("coursename", getCoursename(std.getCourseCode().split(","),course));
				jsArray.add(js);
			});
		});
		return jsArray;
	}

	private String getCoursename(String[] courseCodes, Course course) {
		for(String code: courseCodes) {
			if(course.getCourseCode().equals(code)) {
				return course.getCourseName();
			}
		}
		return null;
	}

	@Override
	public ResponseEntity<?> viewCourse(JSONObject jsonBody) {
		long userId = Long.valueOf(jsonBody.getAsString(Consonant.User_id));
		Professor professor = professorDao.getProfessorById(userId);
		List<Course> courseList = courseService.getCourseByInstructor(professor.getName());
		return new ResponseEntity<>(courseList,HttpStatus.OK);
	}

	
	
}
