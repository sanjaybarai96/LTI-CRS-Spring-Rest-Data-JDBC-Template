/**
 * 
 */
package com.lt.consants;

/**
 * @author user217
 *
 */
public class JDBCSqlConstant {
	
	public final static String UserByUserName="select * from user where userName=?";

	public final static String AllUser ="SELECT * FROM user";
	public final static String AllStudentUser="select * from user where role = ? and isApprove = 0";
	public final static String UpdateSession="update user set session=? where userId=?";
	public final static String UpdateUserPassword="update user set password=? where userId=?";
	public final static String ApproveStudent="update user set isApprove=1 where userId=?";
}
