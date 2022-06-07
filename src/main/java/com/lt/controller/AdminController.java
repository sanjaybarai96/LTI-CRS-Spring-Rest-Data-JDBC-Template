/**
 * 
 */
package com.lt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.dto.Course;
import com.lt.dto.User;
import com.lt.exception.CourseNotAddException;
import com.lt.exception.CourseNotFoundException;
import com.lt.service.AdminService;
import com.lt.service.CourseService;
import com.lt.service.ProfessorService;
import com.lt.service.UserService;

/**
 * @author user215
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProfessorService professorService;

	/**
	 * admin adding course
	 * 
	 * @param course
	 * @return
	 */
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	@ExceptionHandler({ CourseNotAddException.class })
	public ResponseEntity<?> addCourse(@RequestBody Course course) {
		courseService.addCourse(course);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	/**
	 * admin removing course
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/removeCourse/{id}", method = RequestMethod.DELETE)
	@ExceptionHandler({ CourseNotFoundException.class })
	public ResponseEntity<?> removeCourse(@PathVariable("id") String courseCode) {
		courseService.removeCourse(courseCode);
		return new ResponseEntity<>("Removed courseCode :: " + courseCode, HttpStatus.OK);

	}

	/**
	 * Adding professor user by admin
	 * 
	 * @param user
	 */
	 @RequestMapping(value="/addProfessor",method = RequestMethod.POST)
	 public ResponseEntity<User> addProfessor(@RequestBody User user) {
		 adminService.addProfessor(user);
	 return new ResponseEntity<User>(user, HttpStatus.OK);
	 }

	/**
	 * Getting all the student which is not approved
	 * 
	 * @return
	 */
	@RequestMapping(value = "/studentAproval", method = RequestMethod.GET)
	public ResponseEntity<?> studentAproval() {
		List<Map<String,Object>> user = adminService.getStudentList();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Approving student by admin
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/studentAproval/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> studentAproval(@PathVariable("id") long userId) {
		long updatedUserId = adminService.approveStudents(userId);
		if(updatedUserId>0) {
			return new ResponseEntity<>(userId, HttpStatus.OK);
		}
		return new ResponseEntity<>("No Student found for ID " + userId,
				HttpStatus.NOT_FOUND);
	}

}
