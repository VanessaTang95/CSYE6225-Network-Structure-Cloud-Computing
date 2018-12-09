package com.csye6225.fall.Student_Info_System.resouces;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.aopalliance.reflect.Metadata;

import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.datamodel.Student;
import com.csye6225.fall.Student_Info_System.services.StudentService;

@Path("students")
public class StudentResource {
	StudentService studentService=new StudentService();
	
	//get student by department or all students
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByProgram(@QueryParam("department")String department){
		if(department==null) {
			return studentService.getAllStudents();
		}
		return studentService.getStudentByDepartment(department);
	}

	//get Students by course
	@GET
	@Path("/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsByCourse(@PathParam("course")String course){
		if(course==null) return studentService.getAllStudents();
		return studentService.getStudentByCourse(course);
	}
	
	//get Student by id
	@GET
	@Path("studentId/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId")String studentId) {
		return studentService.getStudent(studentId);
	}
	
	//Delete
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId")String studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	//add
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student s) {
		return studentService.addStudent(s);
	}
	
	//register
	
	@POST
	@Path("/{studentId}/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student registerCourses(@PathParam("studentId")String studentId, Course course) throws Exception {
		return studentService.registerCourses(studentId, course.getCourseId());
	}
	
	
	//update
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId")String studentId,Student s) {
		return studentService.updateStuInfo(studentId, s);
	}
	
	
}
