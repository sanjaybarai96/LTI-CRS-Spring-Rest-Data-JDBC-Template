package com.lt.service;

import java.util.List;
import java.util.Map;

public interface AdminServiceInterface {

	
	public List<Map<String,Object>> getStudentList();
	public long approveStudents(long userId);
	
}
