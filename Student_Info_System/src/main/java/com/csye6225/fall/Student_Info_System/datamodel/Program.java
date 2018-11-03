package com.csye6225.fall.Student_Info_System.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Program {
	private String programName;
	private long programId;
	private List<String> coursesOfProgram;
	private List<Student> enrolledStuId;
	private List<Professor> professorsId;
	
	public Program() {
		this.coursesOfProgram=new ArrayList<>();
		this.enrolledStuId=new ArrayList<>();
		this.professorsId=new ArrayList<>();
	}
	
	public Program(long programId,String programName) {
		this();
		this.programId=programId;
		this.programName=programName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public List<String> getCoursesOfProgram() {
		return coursesOfProgram;
	}

	public void setCoursesOfProgram(List<String> coursesOfProgram) {
		this.coursesOfProgram = coursesOfProgram;
	}

	public List<Student> getEnrolledStuId() {
		return enrolledStuId;
	}

	public void setEnrolledStuId(List<Student> enrolledStuId) {
		this.enrolledStuId = enrolledStuId;
	}

	public List<Professor> getProfessorsId() {
		return professorsId;
	}

	public void setProfessorsId(List<Professor> professorsId) {
		this.professorsId = professorsId;
	}
	
	
}
