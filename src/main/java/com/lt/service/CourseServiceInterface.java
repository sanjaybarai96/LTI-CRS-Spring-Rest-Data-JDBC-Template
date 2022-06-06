package com.lt.service;

import java.util.List;

import com.lt.dto.Course;

public interface CourseServiceInterface {

	public List<Course> getCourses();
	public List<Course> getCourseByCourseCode(List<String> courseCode);

}
