/**
 * 
 */
package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author user217
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class CourseNotAddException extends RuntimeException{
	public CourseNotAddException(String courseCode) {
		System.out.println("Course not added :: "+courseCode);
	}

}
