package com.lt.service;

import java.util.List;
import java.util.Map;

import com.lt.dto.Course;

public interface CourseServiceInterface {

	public List<Map<String,Object>> getCourses();
	public List<Course> getCourseByCourseCode(List<String> courseCode);
	public void addCourse(Course course);
	public void removeCourse(String courseCode);
	public List<Course> getCourseByInstructor(String professorName);


}
