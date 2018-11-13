package com.csye6225.fall.Student_Info_System.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;
import com.csye6225.fall.Student_Info_System.datamodel.Professor;


public class ProfessorService {
	//static HashMap<String, Professor> prof_Map = InMemoryDatabase.getProDB();
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper;
	
	public ProfessorService() {
		dynamoDB=new DynamoDBConnector();
		dynamoDB.init();
		mapper=new DynamoDBMapper(dynamoDB.getClient());
	}
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		/*//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list ;*/
		return mapper.scan(Professor.class, new DynamoDBScanExpression());
	}
/*
	// Getting One Professor by hashkey id
		public Professor getProfessor(String Id) {
			Professor pro=mapper.load(Professor.class,Id);
			return pro;
		}*/
		
		public Professor getProfessorByProfessorId(String professorId) {
			List<Professor> list=mapper.scan(Professor.class, new DynamoDBScanExpression());
			for(Professor f:list) {
				if(f.getProfessorId().equals(professorId)) {
					return f;
				}
			}
			return null;
		}
		
		// Get professors in one Department
		public List<Professor> getProfessorsByDepartment(String department) {	
			//Getting the list
			List<Professor> list = getAllProfessors();
			List<Professor> result=new ArrayList<>();
			for (Professor prof : list) {
				if(prof.getDepartment().contains(department)) {
					result.add(prof);
				}
			}
			return result ;
		}
	
	// Adding a professor
	public void addProfessor(String professorId,String firstname,String lastname, Date joiningDate,String department) {
		// Next Id 
		/*long nextAvailableId = prof_Map.size() + 1;
		
		//Create a Professor Object
		Professor prof = new Professor(firstname,lastname,joiningDate.toString(),department);
		prof_Map.put(Long.toString(nextAvailableId), prof);*/
		Professor newOne=new Professor(professorId,firstname,lastname,joiningDate.toString(),department);
		mapper.save(newOne);
	}
	
	public Professor addProfessor(Professor prof) {/*	
		long nextAvailableId = prof_Map.size() + 1;
		prof.setProfessorId(Long.toString(nextAvailableId));
		prof_Map.put(Long.toString(nextAvailableId), prof);
		return prof_Map.get(nextAvailableId);*/
		mapper.save(prof);
		return mapper.load(Professor.class,prof.getId());
	}
	
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		/*Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;*/
		
		Map<String, AttributeValue> map=new HashMap<>();
		map.put(":professorId", new AttributeValue().withS(profId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("professorId=:professorId").withExpressionAttributeValues(map);
		List<Professor> target=mapper.scan(Professor.class, scanExpression);
		if(target.size()!=0) {
			mapper.delete(target.get(0));
			return target.get(0);
		}
		return null;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {	
		/*Professor oldProfObject = prof_Map.get(profId);
		profId = oldProfObject.getProfessorId();
		prof.setProfessorId(profId);
		// Publishing New Values
		prof_Map.put(profId, prof) ;
		
		return prof;*/
		
		Map<String, AttributeValue> map=new HashMap<>();
		map.put(":professorId", new AttributeValue().withS(profId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("professorId=:professorId").withExpressionAttributeValues(map);
		List<Professor> target=mapper.scan(Professor.class, scanExpression);
		if(target.size()!=0) {
			String Id=target.get(0).getId();
			prof.setId(Id);
			mapper.save(prof);
			return mapper.load(Professor.class,Id);
		}
		return null;
	}

}
