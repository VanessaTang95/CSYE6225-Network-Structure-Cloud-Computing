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
import javax.ws.rs.core.MediaType;

import com.csye6225.fall.Student_Info_System.datamodel.Program;
import com.csye6225.fall.Student_Info_System.services.ProgramService;


@Path("programs")
public class ProgramResource {
	ProgramService programService=new ProgramService();
	
	//get all programs
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms(){
		return programService.getAllPrograms();
	}

	//get program by id
	@GET
	@Path("/getOneProgram/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId")long programId) {
		return programService.getOneProgram(programId);
	}
	
	//Delete
	@DELETE
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId")long programId) {
		return programService.deleteProgram(programId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program p) {
		return programService.addProgram(p);
	}
	
	
		public void addProgram(String programName) {
			programService.addProgram(programName);
	}
	
	//update
	@PUT
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId")long programId,Program p) {
		return programService.updateProgramInfo(programId, p);
	}
	
}

