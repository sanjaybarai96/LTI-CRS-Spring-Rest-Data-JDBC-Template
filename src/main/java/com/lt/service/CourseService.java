package com.lt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.dao.CourseDAOImpl;
import com.lt.dto.Course;

@Service
public class CourseService implements CourseServiceInterface {
	
	@Autowired
	CourseDAOImpl courseDao;
	
	
	public List<Course> getCourses() {
		return courseDao.getAllCourse();
	}

	public List<Course> getCourseByCourseCode(List<String> courseCodes) {
		return courseDao.getCourseByCourseCode(courseCodes);
	}

}
