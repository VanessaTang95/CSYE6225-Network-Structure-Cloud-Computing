package com.csye6225.fall.Student_Info_System.resouces;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall.Student_Info_System.datamodel.Student;
import com.csye6225.fall.Student_Info_System.services.StudentService;

@Path("students")
public class StudentResource {
	StudentService studentService=new StudentService();
	
	//get student by program
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByProgram(@QueryParam("program")String program){
		if(program==null) {
			return studentService.getAllStudents();
		}
		return studentService.getStudentByProgram(program);
	}

	//get Students by course
	@GET
	@Path("{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByCourse(@PathParam("course")String course){
		if(course==null) return studentService.getAllStudents();
		return studentService.getStudentByCourse(course);
	}
	
	//get Student by id
	@GET
	@Path("studentId/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId")long studentId) {
		return studentService.getStudent(studentId);
	}
	
	//Delete
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId")long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	//add
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student s) {
		return studentService.addStudent(s);
	}
	
	//update
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId")long studentId,Student s) {
		return studentService.updateStuInfo(studentId, s);
	}
	
	
}
