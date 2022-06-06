package com.lt.dao;



import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lt.configuration.JDBCConfiguration;
import com.lt.dto.Student;
import com.lt.dto.User;


/**
 * @author user217
 *
 */
/**
 * @author user217
 *
 */
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
	public User getUserByUserName(String username) {
		String sql = "select * from user where userName=?";
		User user= jdbcConfiguration.jdbcTemplate().queryForObject(sql,User.class,username);
		return user;
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
	public List<User> getAllStudentUser() {
		
		
		return null;
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
	
}
