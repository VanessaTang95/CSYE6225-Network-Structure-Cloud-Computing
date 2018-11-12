package com.csye6225.fall.Student_Info_System.services;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;

	public class CourseService {
	//	static HashMap<Long,Course> course_Map=InMemoryDatabase.getCourseDB();
		static DynamoDBConnector dynamoDb;
		DynamoDBMapper mapper;
		
		public CourseService() {
			dynamoDb=new DynamoDBConnector();
			dynamoDb.init();
			mapper=new DynamoDBMapper(dynamoDb.getClient());
		}
		
		//getting course by courseId
				public Course getOneCourse(String courseId) {
					List<Course> courses=mapper.scan(Course.class, new DynamoDBScanExpression());
					Course target=new Course();
					for(Course c:courses) {
						if(c.getCourseId().equals(courseId)){
							target=c;
						}
					}
					return target;
				}
				
				
		//getting course by Id
				public Course getCourseById(String Id) {
					return mapper.load(Course.class,Id);
				}
				
				
		////get courses by a department
				public List<Course> getCourseByDepartment(String department){
					List<Course> list=mapper.scan(Course.class, new DynamoDBScanExpression());
					List<Course> target=new ArrayList<>();
					for(Course c:list) {
						if(c.getDepartment().equals(department))
							target.add(c);
					}
					return target;
				}
		
	//getting a list of all courses
		public List<Course> getAllCourse(){
			List<Course> courses=mapper.scan(Course.class, new DynamoDBScanExpression());
			return courses;
		}

	//getting courses by professor by using expression attribute value
		public List<Course> getCourseByProfessor(String professorId){
			Map<String,AttributeValue> eav=new HashMap<>();
			eav.put(":val", new AttributeValue().withS(professorId));
			
			DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
					.withFilterExpression("professorId=:val").withExpressionAttributeValues(eav);
			List<Course> target=mapper.scan(Course.class,scanExpression);
			return target;
		}
		
		
		
	//adding a course
		public void addCourse(String courseId, String courseName, String professorId, String taId, String department,
				String boardId, List<String> roster, List<String> lectures) {
			Course course=new Course(courseId, courseName, professorId, taId, department,
			boardId, roster, lectures);
			mapper.save(course);
		}
		
		
		public Course addCourse(Course course) {
			mapper.save(course);
			return course;
		}
		
		
		//deleting one course
		public Course deleteCourse(String courseId) {
			List<Course> list=mapper.scan(Course.class, new DynamoDBScanExpression());
			Course target=new Course();
			for(Course c:list) {
				if(c.getCourseId().equals(courseId)) {
					target=c;
					mapper.delete(c);
				}
			}
			return target;
		}
		
		//updating Course 
		public Course updateCourseInfo(String courseId, Course course) {
			List<Course> list=mapper.scan(Course.class, new DynamoDBScanExpression());
			for(Course c:list) {
				if(c.getCourseId().equals(courseId)) {
					String Id=c.getId();
					course.setId(Id);
					mapper.save(course);
					return mapper.load(Course.class,course.getId());
				}
			}
			return null;
		}
		
		
		
	}

