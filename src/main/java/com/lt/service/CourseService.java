package com.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.dao.CourseDAOImpl;
import com.lt.dto.Course;
import com.lt.exception.CourseNotAddException;
import com.lt.exception.CourseNotFoundException;

@Service
public class CourseService implements CourseServiceInterface {
	
	@Autowired
	CourseDAOImpl courseDao;
	
	
	public List<Map<String,Object>> getCourses() {
		List<Map<String,Object>> course = courseDao.getAllCourse();
		return course;
	}

	public List<Course> getCourseByCourseCode(List<String> courseCodes) {
		return courseDao.getCourseByCourseCode(courseCodes);
	}

	public void addCourse(Course course) throws CourseNotAddException {
		String courseCode = courseDao.saveCourse(course);
		if(courseCode==null) {
			throw new CourseNotAddException(courseCode);
		}
	}

	public void removeCourse(String courseCode) {
		boolean status = courseDao.removeCourse(courseCode);
		if(!status) {
			throw new CourseNotFoundException(courseCode);
		}
	}

	public List<Course> getCourseByInstructor(String professorName) {
		List<Course> professor = courseDao.getCourseByInstructor(professorName);
		return professor;
	}

}
