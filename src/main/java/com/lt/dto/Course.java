package com.lt.dto;

import java.util.HashMap;
import java.util.Map;

public class Course {

	private String courseCode;
	private String coursename;
	private boolean isOffered;
	private String instructor;
	private double price;
	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return coursename;
	}
	public void setCourseName(String name) {
		this.coursename = name;
	}
	public boolean isOffered() {
		return isOffered;
	}
	public void setOffered(boolean isOffered) {
		this.isOffered = isOffered;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Map<String, Object> toMap() {
		 Map<String, Object> values = new HashMap<>();
		  values.put("courseCode", courseCode);
		  values.put("coursename", coursename);
		  values.put("isOffered", isOffered);
		  values.put("instructor", instructor);
		  values.put("price", price);
		  return values;
	}
}
