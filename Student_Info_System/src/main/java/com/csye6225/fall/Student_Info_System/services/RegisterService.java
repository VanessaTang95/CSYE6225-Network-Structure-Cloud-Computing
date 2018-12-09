package com.csye6225.fall.Student_Info_System.services;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

public class RegisterService {
	private AmazonSNS sns=AmazonSNSClientBuilder
			.standard()
			.withRegion(Regions.US_WEST_2)
			.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
			.build();
	public RegisterService() {};
	
	public String generateTopicArn(String courseId) {
		CreateTopicRequest createTopicRequest=new CreateTopicRequest().withName(courseId);
		CreateTopicResult createTopicResult=sns.createTopic(createTopicRequest);
		String topicArn=createTopicResult.getTopicArn();
		return topicArn;
	}
	
	public void subscribeToSNS(String topicArn, String emailId) {
		sns.subscribe(new SubscribeRequest(topicArn, "email", emailId));
		System.out.println("Successfully subscribe to SNS tipic");
	}
}
