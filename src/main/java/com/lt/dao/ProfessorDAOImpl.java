package com.lt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lt.configuration.JDBCConfiguration;
import com.lt.dto.Professor;
import com.lt.mapper.ProfessorMapper;

@Repository
public class ProfessorDAOImpl implements ProfessorDAO {

	@Autowired
	JDBCConfiguration jdbcConfiguration;

	public Professor getProfessorById(long userId) {
		String sql = "select * from professor where professorId=?";
		Professor professor = jdbcConfiguration.jdbcTemplate().queryForObject(sql, new Object[] { userId },
				new ProfessorMapper());
		return professor;

	}

	public long saveProfessor(Professor professor) {
		String sql = "insert into professor values (?,?)";

		jdbcConfiguration.jdbcTemplate().update(sql, professor.getProfessorId(), professor.getName());
		return professor.getProfessorId();
	}

}
