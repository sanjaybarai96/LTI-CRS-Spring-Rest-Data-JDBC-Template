package com.lt.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lt.configuration.JDBCConfiguration;
import com.lt.dto.RegisterCourse;
import com.lt.dto.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);
	
	@Autowired
	JDBCConfiguration jdbcConfiguration;
	
	public long saveCourseRegistration(RegisterCourse registerCourse) {
		SimpleJdbcInsert simpleInsertJdbcInsert = new SimpleJdbcInsert(jdbcConfiguration.jdbcTemplate())
				.withTableName("registercourse")
				.usingGeneratedKeyColumns("studentId");
		
		return simpleInsertJdbcInsert.executeAndReturnKey(registerCourse.toMap()).longValue();
	}

	public Student getStudentByID(Number userId) {
		String sql = "select * from student where userId=?";
		Student student = jdbcConfiguration.jdbcTemplate().queryForObject(sql,Student.class,userId);
		return student;
	}

	public long updateStudent(Student student, long userId) {
		String sql = "update student set branch=?,coursecode=? where userId=?";
		jdbcConfiguration.jdbcTemplate().update(sql,student.getBranch(),student.getCourseCode(),userId);
		logger.info("student branch update for id ::"+userId);
		return userId;
	}

	public List<Student> getStudentByCourseCodes(List<String> courseCodes) {
		String sql = "selec * from student where coursecode like %s";
		String parameter = courseCodes.stream().map(code->"%"+code+"%").collect(Collectors.joining("or"));
		List<Student> studentList = jdbcConfiguration.jdbcTemplate().queryForList(String.format(sql, parameter),Student.class);
		return studentList;
	}
}
