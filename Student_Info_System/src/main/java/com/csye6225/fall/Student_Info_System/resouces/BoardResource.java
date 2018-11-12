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

import com.csye6225.fall.Student_Info_System.datamodel.Board;
import com.csye6225.fall.Student_Info_System.services.BoardService;

@Path("boards")
public class BoardResource {
	BoardService bs=new BoardService();
	
	//get
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getAllBoards(){
		return bs.getAllBoards();
	}
	
	//get by boardId
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getOneBoard(@PathParam("boardId")String boardId) {
		return bs.getOneBoard(boardId);
	}
	
	//add new board
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board b) {
		return bs.addBoard(b);
	}
	
	//delete
	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("boardId")String boardId) {
		return bs.deleteBoard(boardId);
	}
	
	//update
	@PUT
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("boardId")String boardId,Board b) {
		return bs.updateBoard(boardId, b);
	}
}
