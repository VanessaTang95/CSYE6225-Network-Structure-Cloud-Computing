package com.csye6225.fall.Student_Info_System.database;

import java.util.HashMap;

import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.datamodel.Lecture;
import com.csye6225.fall.Student_Info_System.datamodel.Professor;
import com.csye6225.fall.Student_Info_System.datamodel.Program;
import com.csye6225.fall.Student_Info_System.datamodel.Student;

public class InMemoryDatabase {
	private static HashMap<Long,Student> stuDB=new HashMap<>();
	private static HashMap<String,Professor> proDB=new HashMap<>();
	private static HashMap<Long, Course> courseDB=new HashMap<>();
	private static HashMap<Long,Lecture> lectureDB=new HashMap<>();
	private static HashMap<Long,Program> programDB=new HashMap<>();
	
	public static HashMap<Long, Student> getStuDB() {
		return stuDB;
	}
	
	public static HashMap<String, Professor> getProDB() {
		return proDB;
	}
	public static HashMap<Long, Course> getCourseDB() {
		return courseDB;
	}
	public static HashMap<Long, Lecture> getLectureDB() {
		return lectureDB;
	}
	public static HashMap<Long, Program> getProgramDB() {
		return programDB;
	}

	

}
