package com.csye6225.fall.Student_Info_System.services;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;
import com.csye6225.fall.Student_Info_System.datamodel.Registrar;

public class RegistrarService {
	static DynamoDBConnector connector;
	DynamoDBMapper mapper;
	
	public RegistrarService() {
		connector=new DynamoDBConnector();
		connector.init();
		mapper=new DynamoDBMapper(connector.getClient());
	}
	
	//get
	public List<Registrar> getAll(){
		DynamoDBScanExpression dbScanExpression=new DynamoDBScanExpression();
		return mapper.scan(Registrar.class, new DynamoDBScanExpression());
	}
	
	//add
	public Registrar add(Registrar r) {
		mapper.save(r);
		return r;
	}
	
}
