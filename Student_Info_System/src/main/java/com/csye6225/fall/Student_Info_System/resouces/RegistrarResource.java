package com.csye6225.fall.Student_Info_System.resouces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall.Student_Info_System.datamodel.Registrar;
import com.csye6225.fall.Student_Info_System.services.RegistrarService;

@Path("registrar")
public class RegistrarResource {
	RegistrarService regisService=new RegistrarService();
	
	//get all
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registrar> getAll(){
		return regisService.getAll();
	}
	
	
	
	//add
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Registrar add(Registrar r) {
		return regisService.add(r);
	}
}
