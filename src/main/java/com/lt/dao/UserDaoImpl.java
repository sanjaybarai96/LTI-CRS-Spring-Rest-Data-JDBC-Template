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
	 *get user method
	 */
	@Override
	public Map<String,Object> getUserByUserName(String username) {
		String sql = "select * from user where userName=?";
//		User user= jdbcConfiguration.jdbcTemplate().queryForObject(sql,User.class,username);
		Map<String,Object> userMap = jdbcConfiguration.jdbcTemplate().queryForMap(sql, username);
		
		return userMap;
	}

	/**
	 *getList All user
	 */
	@Override
	public List<User> getAllUser() {
		String sql = "SELECT * FROM user";
	     
	    return jdbcConfiguration.jdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(User.class)); 
	}

	/**
	 * get All Student User
 	 */
	@Override
	public List<Map<String,Object>> getAllStudentUser() {
		String sql = "select * from user where role = ? and isApprove = 0";
		List<Map<String,Object>> studentUsers = jdbcConfiguration.jdbcTemplate().queryForList(sql,Role.Student.name());
		return studentUsers;
	}

	
	/**
	 * saved user method
	 */
	@Override
	public long saveUser(User user) {
		SimpleJdbcInsert simpleInsertJdbcInsert = new SimpleJdbcInsert(jdbcConfiguration.jdbcTemplate())
				.withTableName("user")
				.usingGeneratedKeyColumns("userId");
		return simpleInsertJdbcInsert.executeAndReturnKey(user.toMap()).longValue();
	}

	public void updateSession(long userId,boolean session) {
	String sql ="update user set session=? where userId=?";
	jdbcConfiguration.jdbcTemplate().update(sql,session,userId);
	logger.info("User password updated ::"+userId);		
	}





	/**
	 *update password
	 */
	@Override
	public long updateUserPassword(long userId,String newPassword) {
		String sql = "update user set password=? where userId=?";
		jdbcConfiguration.jdbcTemplate().update(sql,newPassword,userId);
		logger.info("User password updated ::"+userId);
		return userId;
	}

	public long approveStudent(long userId) {
		String sql = "update user set isApprove=1 where userId=?";
		jdbcConfiguration.jdbcTemplate().update(sql,userId);
		logger.info("User approved ::"+userId);
		return userId;
	}
	
}
