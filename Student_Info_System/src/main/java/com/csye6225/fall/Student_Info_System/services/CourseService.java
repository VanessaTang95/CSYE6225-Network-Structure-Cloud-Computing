package com.csye6225.fall.Student_Info_System.services;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;

import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.Course;

	public class CourseService {
		static HashMap<Long,Course> course_Map=InMemoryDatabase.getCourseDB();

	//getting a list of all courses
		public List<Course> getAllCourse(){
			ArrayList<Course> list=new ArrayList<>();
			for(Course c:course_Map.values()) {
				list.add(c);
			}
			return list;
		}

	//adding a course
		public void addCourse(String courseName, long pro_id) {
				if(course_Map.containsKey(pro_id)) {
					throw new IllegalArgumentException("This courseId have already been taken!");
				}
			
			Course c=new Course(pro_id,courseName);
			course_Map.put(pro_id, c);
				
		}
		
		public Course addCourse(Course course) {
			course_Map.put(course.getCourseId(), course);
			return course;
		}
		
		//getting one course
		public Course getOneCourse(Long courseId) {
			return course_Map.get(courseId);
		}
		
		//deleting one course
		public Course deleteCourse(Long courseId) {
			Course del=course_Map.get(courseId);
			course_Map.remove(courseId);
			return del;
		}
		
		//updating Course 
		public Course updateCourseInfo(Long id, Course c) {
			Course old=course_Map.get(id);
			id=old.getCourseId();
			c.setCourseId(id);	
			course_Map.put(id, c);
			return c;
		}
		
		
		//get courses in a program
		public List<Course> getCourseByProgram(long programId){
			ArrayList<Course> list=new ArrayList<>();
			for(Course c:course_Map.values()) {
				if(c.getProgramId()==programId) {
					list.add(c);
				}
			}
			return list;
		}
	}

