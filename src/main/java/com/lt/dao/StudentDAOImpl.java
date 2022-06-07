package com.lt.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.It.mapper.StudentMapper;
import com.lt.configuration.JDBCConfiguration;
import com.lt.dto.RegisterCourse;
import com.lt.dto.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);

	@Autowired
	JDBCConfiguration jdbcConfiguration;

	public long saveCourseRegistration(RegisterCourse registerCourse) {
		String sql = "insert into registercourse values(?,?)";
		jdbcConfiguration.jdbcTemplate().update(sql, registerCourse.getStudentId(), registerCourse.getBranch());
		return registerCourse.getStudentId();
	}

	public Student getStudentByID(Number userId) {
		String sql = "select * from student where studentId=?";
		Student student = jdbcConfiguration.jdbcTemplate().queryForObject(sql, new Object[] { userId },
				new StudentMapper());
		return student;
	}

	public long updateStudent(Student student, long userId) {
		String sql = "update student set branch=?,coursecode=? where studentId=?";
		jdbcConfiguration.jdbcTemplate().update(sql,student.getBranch(),student.getCourseCode(),userId);
		logger.info("student branch update for id ::"+userId);
		return userId;
	}

	public List<Student> getStudentByCourseCodes(List<String> courseCodes) {
		String sql = "select * from student where %s";
		String parameter = courseCodes.stream().map(code -> "coursecode like '%" + code + "%'").collect(Collectors.joining(" or "));
		List<Student> studentList = jdbcConfiguration.jdbcTemplate().query(String.format(sql, parameter),
				new StudentMapper());
		return studentList;
	}

	@Override
	public long saveStudent(Student student) {
		String sql = "insert into student values(?,?,?)";

//		SimpleJdbcInsert simpleInsertJdbcInsert = new SimpleJdbcInsert(jdbcConfiguration.jdbcTemplate())
//				.withTableName("student");

//		 simpleInsertJdbcInsert.execute(student.toMap());
		jdbcConfiguration.jdbcTemplate().update(sql, student.getStudentId(), student.getBranch(),
				student.getCourseCode());
		return student.getStudentId();

	}
}
