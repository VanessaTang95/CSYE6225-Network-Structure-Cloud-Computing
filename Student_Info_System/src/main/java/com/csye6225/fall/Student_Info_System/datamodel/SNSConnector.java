package com.csye6225.fall.Student_Info_System.datamodel;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SNSConnector {
 private static AmazonSNS sns;
 public static void init() {
	 if(sns==null) {
		 AWSCredentialsProvider credentialsProvider;
		 try {
			 credentialsProvider=new InstanceProfileCredentialsProvider(false);
			 credentialsProvider.getCredentials();
		 }catch(Exception e) {
			 System.out.println("Get credentials from profile");
			 credentialsProvider=new ProfileCredentialsProvider();
			 credentialsProvider.getCredentials();
		 }
		 sns=AmazonSNSClientBuilder
				 .standard()
				 .withCredentials(credentialsProvider)
				 .withRegion(Regions.US_WEST_2)
				 .build();
		 System.out.println("SNSClient creat successful!");
	 }
 }
 public AmazonSNS getClient() {
	 return sns;
 }
}
