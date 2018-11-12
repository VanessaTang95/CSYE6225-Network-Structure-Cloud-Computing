package com.csye6225.fall.Student_Info_System.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;
import com.csye6225.fall.Student_Info_System.datamodel.Program;

public class ProgramService {
	//static HashMap<Long,Program> program_Map=InMemoryDatabase.getProgramDB();
	static DynamoDBConnector dynamoDB;
	static DynamoDBMapper mapper;

	public ProgramService() {
		dynamoDB=new DynamoDBConnector();
		dynamoDB.init();
		mapper=new DynamoDBMapper(dynamoDB.getClient());
	}
	
//getting a list of all programs
	public List<Program> getAllPrograms(){
		return mapper.scan(Program.class, new DynamoDBScanExpression());
	}
	
	//getting one program
		public Program getOneProgram(String programId) {
			Map<String,AttributeValue> eav=new HashMap<>();
			eav.put(":val",new AttributeValue().withS(programId));
			DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
					.withFilterExpression("ProgramId=:val").withExpressionAttributeValues(eav);
			List<Program> target=mapper.scan(Program.class, scanExpression);
			if(target.size()!=0) {
				return target.get(0);
			}
			return null;
		}
		

//adding a Program
	public void addProgram(String programId,String programName) {
		Program newProgram=new Program(programId,programName);
		mapper.save(newProgram);
	}
	
	public Program addProgram(Program p) {
		mapper.save(p);
		return mapper.load(Program.class,p.getId());
	}
	
	
	//deleting one program
	public Program deleteProgram(String programId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val",new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("ProgramId=:val").withExpressionAttributeValues(eav);
		List<Program> target=mapper.scan(Program.class, scanExpression);
		if(target.size()!=0) {
			Program removedProgram=target.get(0);
			mapper.delete(removedProgram);
			return removedProgram;
		}
		return null;
	}
	
	//updating program
	public Program updateProgramInfo(String programId, Program p) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val",new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("ProgramId=:val").withExpressionAttributeValues(eav);
		List<Program> target=mapper.scan(Program.class, scanExpression);
		if(target.size()!=0) {
			String Id=target.get(0).getId();
			p.setId(Id);
			mapper.save(p);
			return mapper.load(Program.class,Id);
		}
		return null;	
	}
}
