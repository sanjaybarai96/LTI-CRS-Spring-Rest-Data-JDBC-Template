package com.lt.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author user217
 *
 */
public class RegisterCourse {
	private long studentId;
	private String branch;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public Map<String,Object> toMap(){
		 Map<String, Object> values = new HashMap<>();
		  values.put("studentId", studentId);
		  values.put("branch", branch);
		  return values;
	}
}
