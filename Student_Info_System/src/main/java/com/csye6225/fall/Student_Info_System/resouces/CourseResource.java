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
	
	//get student by program
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByProgram(@QueryParam("programId")long programId){
		if(programId==0) {
			return courseservice.getAllCourse();
		}
		return courseservice.getCourseByProgram(programId);
	}
	
	//get Course by id
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId")long courseId) {
		return courseservice.getOneCourse(courseId);
	}
	
	//Delete
	@DELETE
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("courseId")long courseId) {
		return courseservice.deleteCourse(courseId);
	}
	
	//add
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
	public Course updateCourse(@PathParam("courseId")long courseId,Course c) {
		return courseservice.updateCourseInfo(courseId, c);
	}
	
	
}

