package com.csye6225.fall.Student_Info_System.datamodel;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConnector {
	static AmazonDynamoDB dynamoDb;
	
	public static void init() {
		if(dynamoDb==null) {
		AWSCredentialsProvider cp;
			try {
			cp=new InstanceProfileCredentialsProvider(false);//cloud
			cp.getCredentials();
		}catch(Exception e) {
			cp=new ProfileCredentialsProvider();//local 
			cp.getCredentials();	
		}
			
		
		dynamoDb=AmazonDynamoDBClientBuilder
				.standard()
				.withCredentials(cp)
				.withRegion("us-west-2")
				.build();
		System.out.println("Create client successful!");
		}
	}

	public static AmazonDynamoDB getClient() {
		return dynamoDb;
	}
}