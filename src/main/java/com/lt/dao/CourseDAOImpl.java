package com.lt.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lt.configuration.JDBCConfiguration;
import com.lt.dto.Course;


@Repository
public class CourseDAOImpl implements CourseDAO{

	@Autowired
	JDBCConfiguration jdbcConfiguration;
	
	@Override
	@Transactional
	public List<Course> getAllCourse() {
		String sql = "select * from course";
		List<Course> courses = jdbcConfiguration.jdbcTemplate().queryForList(sql,Course.class);
		return courses;
	}

	@Override
	@Transactional
	public List<Course> getCourseByCourseCode(List<String> courseCodes) {
		String inSql = String.join(",", Collections.nCopies(courseCodes.size(), "?"));
		String sql = "select * from course where coursecode in (%s)";
		List<Course> courses = jdbcConfiguration.jdbcTemplate().queryForList(String.format(sql, inSql), Course.class);
		return courses;
	}

	@Override
	@Transactional
	public String saveCourse(Course course) {
		SimpleJdbcInsert simpleInsertJdbcInsert = new SimpleJdbcInsert(jdbcConfiguration.jdbcTemplate())
				.withTableName("course")
				.usingGeneratedKeyColumns("courseCode");
		
		return simpleInsertJdbcInsert.executeAndReturnKey(course.toMap()).toString();
	}

	public boolean removeCourse(String courseCode) {
		String sql = "delete course where coursecode=?";
		return jdbcConfiguration.jdbcTemplate().update(sql,courseCode)>0;
	}

	public List<Course> getCourseByInstructor(String professorName) {
		String sql = "select * from course where instructorname = ?";
		List<Course> courseList = jdbcConfiguration.jdbcTemplate().queryForList(sql,Course.class,professorName);
		return courseList;
	}

}
