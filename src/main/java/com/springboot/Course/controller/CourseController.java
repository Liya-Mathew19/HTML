package com.springboot.Course.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Course.model.Course;
import com.springboot.Course.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService service;
	
	/****************************************************************ADD NEW DATA**********************************************************/
	
	@RequestMapping(value="/addCourse",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCourse(@RequestBody Course c) {
		
		boolean insert_status = service.addCourse(c);
		
		if(insert_status == true) {
			return "Course added successfully !!!";
		}
		else {
			return "Can't add the course, Something went wrong !!!";
		}
		
	}
	
	/*******************************************************UPDATE AN EXISTING DATA*******************************************************/
	
	@RequestMapping(value="/updateCourse",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCourse(@RequestBody Course c) {
			
		if(Objects.isNull(c.getCourseId()) || c.getCourseId()==0) {
			return "Error : Course ID cannot be NULL or 0 !!";
		}
		
		boolean update_status = service.updateCourse(c);
			
		if(update_status == true) {
			return "Course updated successfully !!!";
		}
		else {
			return "Can't update the course, Something went wrong !!!";
		}
			
	}
	
	/*****************************************************FETCH ALL VALUES FROM TABLE******************************************************/
	
	@RequestMapping(value="/getAllCourses",method = RequestMethod.GET)
	public List<Course> getAllCourses() {
		
		List<Course> cList = service.getAllCourses();
		return cList;
		
	}
	
	/*****************************************FETCH A SINGLE ROW BY ID USING @RequestBody**************************************************/
	
	@RequestMapping(value="/getCourseById",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Course> getCourseById(@RequestBody Course c) {
		
		if(Objects.isNull(c.getCourseId()) || c.getCourseId()==0) {
			throw new IllegalArgumentException("Course ID cannot be NULL or 0 !!");
		}
		
		List<Course> obj = service.getCourseById(c.getCourseId());
		return obj;
		
	}
	
	/*****************************************FETCH A SINGLE ROW BY ID USING @PathVariable*************************************************/
	
	@RequestMapping(value="/getCourseById2/{id}",method = RequestMethod.GET)
	public List<Course> getCourseById2(@PathVariable int id) {
		
		if(Objects.isNull(id) || id==0) {
			throw new IllegalArgumentException("Course ID cannot be NULL or 0 !!");
		}
		
		List<Course> obj = service.getCourseById(id);
		return obj;
		
	}
	
	/****************************************DELETE A SINGLE ROW BY ID USING @PathVariable***********************************************/
	
	@RequestMapping(value="/deleteCourseById/{id}",method = RequestMethod.DELETE)
	public String deleteCourseById(@PathVariable int id) {
		
		if(Objects.isNull(id) || id==0) {
			return "Error : Course ID cannot be NULL or 0 !!";
		}
		
		boolean status = service.deleteCourseById(id);
		if(status==true)
		{
			return "Course deleted successfully !!!";
		}
		else {
			return "Can't delete the course, Something went wrong !!!";
		}
		
	}
	
/***************************************************DELETE A SINGLE ROW BY ID USING @RequestBody***********************************************************/
	
	@RequestMapping(value="/deleteCourseById2",method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String deleteCourseById2(@RequestBody Course c) {
		
		if(Objects.isNull(c.getCourseId()) || c.getCourseId()==0) {
			return "Error : Course ID cannot be NULL or 0 !!";
		}
		
		boolean status = service.deleteCourseById(c.getCourseId());
		if(status==true)
		{
			return "Course deleted successfully !!!";
		}
		else {
			return "Can't delete the course, Something went wrong !!!";
		}
		
	}
}
