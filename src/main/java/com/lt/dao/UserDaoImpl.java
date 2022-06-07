package com.lt.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lt.configuration.JDBCConfiguration;
import com.lt.consants.JDBCSqlConstant;
import com.lt.consants.Role;
import com.lt.dto.User;

/**
 * @author user217
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	JDBCConfiguration jdbcConfiguration;

	/**
	 * get user method
	 */
	@Override
	public Map<String, Object> getUserByUserName(String username) {
		Map<String, Object> userMap = jdbcConfiguration.jdbcTemplate().queryForMap(JDBCSqlConstant.UserByUserName,
				username);

		return userMap;
	}

	/**
	 * getList All user
	 */
	@Override
	public List<User> getAllUser() {
	

		return jdbcConfiguration.jdbcTemplate().query( JDBCSqlConstant.AllUser, BeanPropertyRowMapper.newInstance(User.class));
	}

	/**
	 * get All Student User
	 */
	@Override
	public List<Map<String, Object>> getAllStudentUser() {
		List<Map<String, Object>> studentUsers = jdbcConfiguration.jdbcTemplate().queryForList(JDBCSqlConstant.AllStudentUser,
				Role.Student.name());
		return studentUsers;
	}

	/**
	 * saved user method
	 */
	@Override
	public long saveUser(User user) {
		SimpleJdbcInsert simpleInsertJdbcInsert = new SimpleJdbcInsert(jdbcConfiguration.jdbcTemplate())
				.withTableName("user").usingGeneratedKeyColumns("userId");
		return simpleInsertJdbcInsert.executeAndReturnKey(user.toMap()).longValue();
	}

	public void updateSession(long userId, boolean session) {
		jdbcConfiguration.jdbcTemplate().update(JDBCSqlConstant.UpdateSession, session, userId);
		logger.info("User password updated ::" + userId);
	}

	/**
	 * update password
	 */
	@Override
	public long updateUserPassword(long userId, String newPassword) {
		jdbcConfiguration.jdbcTemplate().update(JDBCSqlConstant.UpdateUserPassword, newPassword, userId);
		logger.info("User password updated ::" + userId);
		return userId;
	}

	public long approveStudent(long userId) {
		jdbcConfiguration.jdbcTemplate().update(JDBCSqlConstant.ApproveStudent, userId);
		logger.info("User approved ::" + userId);
		return userId;
	}

}
