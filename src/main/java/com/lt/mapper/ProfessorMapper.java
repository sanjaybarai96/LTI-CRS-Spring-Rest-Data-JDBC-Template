package com.lt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.dto.Professor;

public class ProfessorMapper implements RowMapper<Professor> {

	@Override
	public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Professor professor = new Professor();
		professor.setProfessorId(rs.getLong("professorId"));
		professor.setName(rs.getString("professorName"));
		return professor;
	}

	
}
