package com.csye6225.fall.Student_Info_System.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
	private String lec_Name;
	private long lec_Id;
	private List<String> nodes;
	private List<String> materials;
	private long lec_course_id;
	
	public Lecture() {
		this.nodes=new ArrayList<>();
		this.materials=new ArrayList<>();
	}
	
	public Lecture(long lec_id,String name, long course_id) {
		this();
		this.lec_Id=lec_id;
		this.lec_Name=name;
		this.lec_course_id=course_id;
	}

	public String getLec_Name() {
		return lec_Name;
	}

	public void setLec_Name(String lec_Name) {
		this.lec_Name = lec_Name;
	}

	public long getLec_Id() {
		return lec_Id;
	}

	public void setLec_Id(long lec_Id) {
		this.lec_Id = lec_Id;
	}

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	public List<String> getMaterials() {
		return materials;
	}

	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}

	public long getLec_course_id() {
		return lec_course_id;
	}

	public void setLec_course_id(long lec_course_id) {
		this.lec_course_id = lec_course_id;
	}
	

}
