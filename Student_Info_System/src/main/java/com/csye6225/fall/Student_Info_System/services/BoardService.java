package com.csye6225.fall.Student_Info_System.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall.Student_Info_System.datamodel.Board;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;

public class BoardService {
	static DynamoDBConnector dbConnector;
	static DynamoDBMapper mapper;
	
	public BoardService() {
		dbConnector=new DynamoDBConnector();
		dbConnector.init();
		mapper=new DynamoDBMapper(dbConnector.getClient());
	}
	
	//get all
	public List<Board> getAllBoards(){
		return mapper.scan(Board.class, new DynamoDBScanExpression());
	}
	
	//get one
	public Board getOneBoard(String boardId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":boardId", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("boardId=:boardId").withExpressionAttributeValues(eav);
		List<Board> result=mapper.scan(Board.class, scanExpression);
		if(result.size()!=0) {
			return result.get(0);
		}
		return null;
	
	}
	
	//add board
	public Board addBoard(Board b) {
		mapper.save(b);
		return mapper.load(Board.class,b.getId());
	}
	
	public void addBoard(String boardId, String courseId) {
		Board newBoard=new Board(boardId,courseId);
		mapper.save(newBoard);
	}
	
	//delete Board
	public Board deleteBoard(String boardId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":boardId", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("boardId=:boardId").withExpressionAttributeValues(eav);
		List<Board> result=mapper.scan(Board.class, scanExpression);
		if(result.size()!=0) {
			mapper.delete(result.get(0));
			return result.get(0);
		}
		return null;
	}
	
	//update Board
	public Board updateBoard(String boardId,Board b) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":boardId", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("boardId=:boardId").withExpressionAttributeValues(eav);
		List<Board> result=mapper.scan(Board.class, scanExpression);
		if(result.size()!=0) {
			String Id=result.get(0).getId();
			b.setId(Id);
			mapper.save(b);
			return mapper.load(Board.class,Id);
		}
		return null;
	}
}

