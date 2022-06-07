package com.lt.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.dto.Student;

public class StudentMapper implements RowMapper<Student>{

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student student = new Student();
		student.setStudentId(rs.getLong("studentId"));
		student.setBranch(rs.getString("branch"));
		student.setCourseCode(rs.getString("coursecode"));
		return student;
	}


}
