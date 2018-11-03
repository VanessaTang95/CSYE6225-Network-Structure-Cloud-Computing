package com.csye6225.fall.Student_Info_System.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private long studentId;
	private String imageUrl;
	private List<Course> courses;
	private String programName;
	
	public Student() {
		this.courses=new ArrayList<>();
	}
	
	
	
	public Student(long studentId,String name,String imageUrl, String programName) {
		this();
		this.studentId=studentId;
		this.name=name;
		this.imageUrl=imageUrl;
		this.programName=programName;
	}
	
	public Student(long studentId, String name) {
		this(studentId,name,"","");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
