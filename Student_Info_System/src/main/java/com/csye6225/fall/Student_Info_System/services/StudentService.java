package com.csye6225.fall.Student_Info_System.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.Student;

public class StudentService {
	static HashMap<Long,Student> stu_Map=InMemoryDatabase.getStuDB();

//getting a list of all students
	public List<Student> getAllStudents(){
		ArrayList<Student> list=new ArrayList<>();
		for(Student s:stu_Map.values()) {
			list.add(s);
		}
		return list;
	}

//adding a student
	public void addStudent(String name, String image,String programName) {
			//generate next id automatic
		long nextId=stu_Map.size()+1;
		
		Student s=new Student(nextId,name,image,programName);
		stu_Map.put(nextId, s);
			
	}
	
	public Student addStudent(Student s) {
		long nextId=stu_Map.size()+1;
		s.setStudentId(nextId);
		stu_Map.put(nextId, s);
		return stu_Map.get(nextId);
	}
	
	//getting one student
	public Student getStudent(Long stuId) {
		return stu_Map.get(stuId);
	}
	
	//deleting one student
	public Student deleteStudent(Long stuId) {
		Student del=stu_Map.get(stuId);
		stu_Map.remove(stuId);
		return del;
	}
	
	//updating Student id
	public Student updateStuInfo(Long id, Student s) {
		Student oldS=stu_Map.get(id);
		id=oldS.getStudentId();
		s.setStudentId(id);
		stu_Map.put(id, s);
		return s;
	}
	
	//get students in same course
	public List<Student> getStudentByCourse(String course){
		ArrayList<Student> list=new ArrayList<>();
		for(Student s:stu_Map.values()) {
			if(s.getCourses().contains(course)) {
				list.add(s);
			}
		}
		return list;
	}
	
	//get students in a program
	public List<Student> getStudentByProgram(String program){
		ArrayList<Student> list=new ArrayList<>();
		for(Student s:stu_Map.values()) {
			if(s.getProgramName().equals(program)) {
				list.add(s);
			}
		}
		return list;
	}
}
