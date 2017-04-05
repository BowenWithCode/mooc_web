package org.mooc.bean;

import java.util.TreeSet;

/**
 * @author: wuke 
 * @date  : 2016��12��12�� ����9:14:18
 * Title  : UserLearnedCourses
 * Description : store one user's id and the courses id which he has learned
 */
public class UserLearnedCourses {
	String userId;
	TreeSet<String> coursesSet; // the elements are distinct and sorted
	
	public UserLearnedCourses() {
	}
	
	public UserLearnedCourses(String userId, TreeSet<String> coursesSet) {
		this.userId = userId;
		this.coursesSet = coursesSet;
	}
	
	public String getUserId() {
		return userId;
	}
	public TreeSet<String> getCoursesSet() {
		return coursesSet;
	}
}