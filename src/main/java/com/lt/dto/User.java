/**
 * 
 */
package com.lt.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author user215
 *
 */
public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String firstname;
	private String lastName;
	private String userName;
	private String password;
	private String emailId;
	private String dateOfBirth;
	private String address;
	private String location;
	private long pincode;
	private String country;
	private LocalDate createDate;
	private String role;
	private int isApprove;
	private boolean session;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(int isApprove) {
		this.isApprove = isApprove;
	}
	public boolean isSession() {
		return session;
	}
	public void setSession(boolean session) {
		this.session = session;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Map<String, ?> toMap() {
		 Map<String, Object> values = new HashMap<>();
		  values.put("userId", userId);
		  values.put("firstname", firstname);
		  values.put("lastName", lastName);
		  values.put("userName", userName);
		  values.put("password", password);
		  values.put("emailId", emailId);
		  values.put("dateOfBirth", dateOfBirth);
		  values.put("address", address);
		  values.put("location", location);
		  values.put("pincode", pincode);
		  values.put("country", country);
		  values.put("createDate", createDate);
		  values.put("role", role);
		  values.put("isApprove", isApprove);
		  values.put("session", session );
		  
		  
		  return values;
	}
	
	
}
