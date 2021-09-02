package com.springboot.Course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.springboot.Course.model.Course;

@Component
public class CourseDAO {

	@Autowired
	JdbcTemplate template;

	/****************************************************************ADD NEW DATA**********************************************************/
	
	public boolean addCourse(Course c) {
		boolean insert_status = false;
		try {
			String Insert_data = "INSERT INTO `student`.`course` (`courseId`,`name`,`amount`,`field`,`duration`) VALUES("
					+c.getCourseId() + ",'" +c.getName() + "'," +c.getAmount() +",'" +c.getField() + "','" +c.getDuration() +"')";
			template.execute(Insert_data);
			insert_status = true;
		}catch(Exception e) {
			System.out.println(e);
			insert_status = false;
		}
		return insert_status;
		
	}
	
	/*******************************************************UPDATE AN EXISTING DATA*******************************************************/
	
	public boolean updateCourse(Course c) {
		boolean update_status = false;
		try {
			List<Course> clist = this.getAllCourses();
			Optional<Course> course = clist.stream().filter(c1->(c1.getCourseId()==c.getCourseId())).findFirst();
			if(course.isEmpty())
			{
				update_status = false;
			}
			else 
			{
				String update_data = "UPDATE `student`.`course` SET name ='"
					+c.getName()+ "',amount=" + c.getAmount() +",field='" +c.getField() +"',duration='" +c.getDuration() + "' WHERE courseId = " +c.getCourseId();
				template.execute(update_data);
				update_status = true;
			}
		}catch(Exception e) {
			System.out.println(e);
			update_status = false;
		}
		return update_status;
		
	}
	
	/*****************************************************FETCH ALL VALUES FROM TABLE******************************************************/
	
	public List<Course> getAllCourses() {

		String fetch_data = "SELECT * FROM course";
		List<Course> courseList = template.query(fetch_data, new ResultSetExtractor<List<Course>>() {

			@Override
			public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<Course> cList = new ArrayList<Course>();
				while(rs.next()) {
					Course course = new Course();
					course.setCourseId(rs.getInt("courseId"));
					course.setName(rs.getString("name"));
					course.setAmount(rs.getDouble("amount"));
					course.setField(rs.getString("field"));
					course.setDuration(rs.getString("duration"));
					cList.add(course);
				}
				return cList;
			}
		});

		courseList.stream().forEach(course->{
			System.out.println("CourseID: "+course.getCourseId() + "\nName: " + course.getName() + "\nAmount : " 
					+ course.getAmount() + "\nField : " + course.getField()+"\nDuration : " +course.getDuration());
			System.out.println("--------------------------------------------------------------------------------------");
		});
		return courseList;
	}

	/**************************************************FETCH A SINGLE ROW BY ID************************************************************/
	
	public List<Course> getCourseById(int id) {
		
		String fetch_data_byId = "SELECT * FROM course WHERE courseId="+id;
		
		List<Course> courseList = template.query(fetch_data_byId, new ResultSetExtractor<List<Course>>() {

			@Override
			public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<Course> cList = new ArrayList<Course>();
				while(rs.next()) {
					Course course = new Course();
					course.setCourseId(rs.getInt("courseId"));
					course.setName(rs.getString("name"));
					course.setAmount(rs.getDouble("amount"));
					course.setField(rs.getString("field"));
					course.setDuration(rs.getString("duration"));
					cList.add(course);
				}
				return cList;
			}
		});

		courseList.stream().forEach(course->{
			System.out.println("CourseID: "+course.getCourseId() + "\nName: " + course.getName() + "\nAmount : " 
					+ course.getAmount() + "\nField : " + course.getField()+"\nDuration : " +course.getDuration());
			System.out.println("--------------------------------------------------------------------------------------");
		});
		return courseList;
	}
	
	/***************************************************DELETE A SINGLE ROW BY ID***********************************************************/
	
	public boolean deleteCourse(int id) {
		
		boolean delete_status=false;
		
		try {
			List<Course> clist = this.getAllCourses();
			Optional<Course> course = clist.stream().filter(c->(c.getCourseId()==id)).findFirst();
			if(course.isEmpty())
			{
				delete_status = false;
			}
			else {
				String delete_data ="DELETE FROM course WHERE courseId = "+id;
				template.execute(delete_data);
				delete_status=true;
			}
		}catch(Exception e) {
			System.out.println(e);
			delete_status = false;
		}
		
		return delete_status;
	}
}
