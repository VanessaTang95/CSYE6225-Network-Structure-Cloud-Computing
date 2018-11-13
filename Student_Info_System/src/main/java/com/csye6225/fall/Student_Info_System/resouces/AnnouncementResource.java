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

import com.csye6225.fall.Student_Info_System.datamodel.Announcements;
import com.csye6225.fall.Student_Info_System.services.AnnouncementService;

@Path("announcements")
public class AnnouncementResource {
	AnnouncementService a_Service=new AnnouncementService();
	
	//GET
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcements> getAllAnnoucements(){
		return a_Service.getAllAnnouncements();
	}
	
	//get an announcement by annoucementid and boardid
	@GET
	@Path("/{boardId}_{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcements getOneAnnoucement(@PathParam("announcementId")String announcementId,@PathParam("boardId")String boardId) {
		return a_Service.getOneAnnouncement(announcementId, boardId);
	}
	
	//POST
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcements addAnnouncement(Announcements announce) {
		return a_Service.addAnnouncement(announce);
	}
	
	//delete
	@DELETE
	@Path("/{boardId}_{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcements deleteAnnoucement(@PathParam("announcementId")String announcementId,@PathParam("boardId")String boardId) {
		return a_Service.deleteAnnouncement(announcementId, boardId);
	}
	
	//update
	@PUT
	@Path("/{boardId}_{annoucementId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcements updateAnnouncement(@PathParam("announcementId")String announcementId,@PathParam("boardId")String boardId,Announcements a) {
		return a_Service.updateAnnouncement(announcementId, boardId, a);
	}
	
	
}
