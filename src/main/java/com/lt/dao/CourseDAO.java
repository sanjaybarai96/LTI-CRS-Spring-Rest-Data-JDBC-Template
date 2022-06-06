package com.lt.dao;

import java.util.List;

import com.lt.dto.Course;

public interface CourseDAO {

	public List<Course> getAllCourse();
	public List<Course> getCourseByCourseCode(List<String> courseCodes);
	public String saveCourse(Course course);
	public boolean removeCourse(String courseCode);
	public List<Course> getCourseByInstructor(String professorName);
}
