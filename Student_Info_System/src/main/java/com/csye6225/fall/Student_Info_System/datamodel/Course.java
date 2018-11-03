package com.csye6225.fall.Student_Info_System.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private long courseId;
	private String courseName;
	private List<String> lectures;
	private String board;
	private String roster;
	private Professor professor;
	private Student tA;
	private long programId;
	//constructions with no param, one param and two params
	public Course() {
		this.tA=new Student();
		this.professor=new Professor();
		this.lectures=new ArrayList<>();
	}
	
	public Course(long courseId,String name) {
		this();
		this.courseId=courseId;
		this.courseName=name;
	}
	public Course(long courseID) {
		this.courseId=courseID;
	}
	
	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<String> getLectures() {
		return lectures;
	}

	public void setLectures(List<String> lectures) {
		this.lectures = lectures;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getRoster() {
		return roster;
	}

	public void setRoster(String roster) {
		this.roster = roster;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Student gettA() {
		return tA;
	}

	public void settA(Student tA) {
		this.tA = tA;
	}

		
}
