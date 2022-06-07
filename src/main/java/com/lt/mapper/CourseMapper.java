package com.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.dto.Course;

public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		course.setCourseCode(rs.getString("coursecode"));
		course.setCourseName(rs.getString("coursename"));
		course.setOffered(rs.getBoolean("isOffered"));
		course.setInstructor(rs.getString("instructor"));
		course.setPrice(rs.getDouble("price"));
		return course;
	}

}
