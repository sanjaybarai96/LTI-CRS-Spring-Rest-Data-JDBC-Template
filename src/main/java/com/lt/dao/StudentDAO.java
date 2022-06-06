package com.lt.dao;

import com.lt.dto.RegisterCourse;
import com.lt.dto.Student;

public interface StudentDAO {

	public long saveCourseRegistration(RegisterCourse registerCourse);
	
	public Student getStudentByID(Number userId); 
	
	public long updateStudent(Student student, long userId);
	
	public long saveStudent(Student student);
	
}
