package com.csye6225.fall.Student_Info_System.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Professor {
	private String pro_Name;
	private long pro_Id;
	private String joiningDate;
	private List<String> teachingCourses;
	private List<String> belongingProgram;
	
	public Professor() {
		this.belongingProgram=new ArrayList<>();
		this.teachingCourses=new ArrayList<>();
	}
	
	public Professor(String pro_Name,String joingDate) {
		this();
		this.pro_Name=pro_Name;
		this.joiningDate=joingDate;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPro_Name() {
		return pro_Name;
	}

	public void setPro_Name(String pro_Name) {
		this.pro_Name = pro_Name;
	}

	public long getPro_Id() {
		return pro_Id;
	}

	public void setPro_Id(long pro_Id) {
		this.pro_Id = pro_Id;
	}

	public List<String> getTeachingCourses() {
		return teachingCourses;
	}

	public void setTeachingCourses(List<String> teachingCourses) {
		this.teachingCourses = teachingCourses;
	}

	public List<String> getBelongingProgram() {
		return belongingProgram;
	}

	public void setBelongingProgram(List<String> belongingProgram) {
		this.belongingProgram = belongingProgram;
	}
	
	
}
