package com.lt.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lt.configuration.JDBCConfiguration;
import com.lt.dto.Course;
import com.lt.mapper.CourseMapper;

@Repository
public class CourseDAOImpl implements CourseDAO {

	@Autowired
	JDBCConfiguration jdbcConfiguration;

	@Override
	@Transactional
	public List<Map<String,Object>> getAllCourse() {
		String sql = "select * from course";
		List<Map<String,Object>> courses = jdbcConfiguration.jdbcTemplate().queryForList(sql);
		return courses;
	}

	@Override
	@Transactional
	public List<Course> getCourseByCourseCode(List<String> courseCodes) {
		String inSql = String.join(",", Collections.nCopies(courseCodes.size(), "?"));
		String sql = "select * from course where coursecode in (%s)";
		List<Course> courses = jdbcConfiguration.jdbcTemplate().query(String.format(sql, inSql),
				courseCodes.toArray(),new CourseMapper());
		return courses;
		
	}

	@Override
	@Transactional
	public String saveCourse(Course course) {
		String sql = "insert into course values(?,?,?,?,?)";
		jdbcConfiguration.jdbcTemplate().update(sql, course.getCourseCode(), course.getCourseName(), course.isOffered(),
				course.getInstructor(), course.getPrice());
		return course.getCourseCode();
	}

	public boolean removeCourse(String courseCode) {
		String sql = "delete from course where coursecode=?";
		return jdbcConfiguration.jdbcTemplate().update(sql, courseCode) > 0;
	}

	public List<Course> getCourseByInstructor(String professorName) {
		String sql = "select * from course where instructorname = ?";
		List<Course> courseList = jdbcConfiguration.jdbcTemplate().queryForList(sql, Course.class, professorName);
		return courseList;
	}

}
