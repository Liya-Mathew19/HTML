package com.springboot.Course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Course.dao.CourseDAO;
import com.springboot.Course.model.Course;

@Service
public class CourseService {

	@Autowired
	private CourseDAO dao;
	
	/****************************************************************ADD NEW DATA**********************************************************/
	
	public boolean addCourse(Course c) {
		boolean insert_status = dao.addCourse(c);
		return insert_status;
	}
	
	/*******************************************************UPDATE AN EXISTING DATA********************************************************/
	
	public boolean updateCourse(Course c) {
		boolean update_status = dao.updateCourse(c);
		return update_status;
	}
	
	/*****************************************************FETCH ALL VALUES FROM TABLE******************************************************/
	
	public List<Course> getAllCourses() {
		List<Course> cList = dao.getAllCourses();
		return cList;
	}

	/**************************************************FETCH A SINGLE ROW BY ID************************************************************/
	
	public List<Course> getCourseById(int id) {
		List<Course> cList = dao.getCourseById(id);
		return cList;
	}

	/***************************************************DELETE A SINGLE ROW BY ID***********************************************************/
	
	public boolean deleteCourseById(int id) {		
		boolean delete_status = dao.deleteCourse(id);
		return delete_status;
	}

}
