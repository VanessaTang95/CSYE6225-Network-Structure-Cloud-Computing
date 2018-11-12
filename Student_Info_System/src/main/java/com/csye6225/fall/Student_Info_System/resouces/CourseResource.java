package com.csye6225.fall.Student_Info_System.resouces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.services.CourseService;


@Path("courses")
public class CourseResource {
	CourseService courseservice=new CourseService();
	
	//get student by department
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByProgram(@QueryParam("department")String department){
		if(department==null) {
			return courseservice.getAllCourse();
		}
		return courseservice.getCourseByDepartment(department);
	}
	
	//get Course by id
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId")String courseId) {
		return courseservice.getOneCourse(courseId);
	}
	
	//get Course by professor
	@GET
	@Path("/professor/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByProfessor(@PathParam("professorId")String professorId ){
		return courseservice.getCourseByProfessor(professorId);
	}
	
	//Delete
	@DELETE
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("courseId")String courseId) {
		return courseservice.deleteCourse(courseId);
	}
	
	//POST
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course c) {
				return courseservice.addCourse(c);
	}
	
	//update
	@PUT
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("courseId")String courseId,Course c) {
		return courseservice.updateCourseInfo(courseId, c);
	}
	
	
}

