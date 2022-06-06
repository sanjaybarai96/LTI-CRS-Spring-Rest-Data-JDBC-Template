package com.lt.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long studentId;
	private String courseCode;
	private String branch;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String course) {
		this.courseCode = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Map<String, ?> toMap() {
		Map<String, Object> values = new HashMap<>();
		  values.put("studentId", studentId);
		  values.put("courseCode", courseCode);
		  values.put("branch", branch);
		  
		  return values;
	}
	


}
