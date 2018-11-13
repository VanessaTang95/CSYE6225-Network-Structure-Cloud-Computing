package com.csye6225.fall.Student_Info_System.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall.Student_Info_System.datamodel.Announcements;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;

public class AnnouncementService {
	static DynamoDBConnector dbConnector;
	static DynamoDBMapper mapper;
	
	public AnnouncementService() {
		dbConnector=new DynamoDBConnector();
		dbConnector.init();
		mapper=new DynamoDBMapper(dbConnector.getClient());
	}
	//get all
	public List<Announcements> getAllAnnouncements() {
		return mapper.scan(Announcements.class, new DynamoDBScanExpression());
	}
	
	//get one
	public Announcements getOneAnnouncement(String announcementId,String boardId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val1", new AttributeValue().withS(announcementId));
		eav.put(":val2", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("announcementId=:val1 and boardId=:val2").withExpressionAttributeValues(eav);
		List<Announcements> result=mapper.scan(Announcements.class, scanExpression);
		if(result.size()!=0) {
			return result.get(0);
		}
		return null;
	}
	
	//add annoucements
	public void addAnnouncement(String annoucementId, String announcementText, String boardId) {
		if(announcementText.length()<=160) {
		Announcements newAnn=new Announcements(annoucementId,announcementText,boardId);
		mapper.save(newAnn);
		}
		else
			System.out.println("----Announcement Text oversize!");
	}
	
	public Announcements addAnnouncement(Announcements a) {
		if(a.getAnnnouncementText().length()<=160) {
			mapper.save(a);
		}
		return mapper.load(Announcements.class,a.getId());
	}
	
	//delete annoucement
	public Announcements deleteAnnouncement(String announcementId,String boardId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val1", new AttributeValue().withS(announcementId));
		eav.put(":val2", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("announcementId=:val1 and boardId=:val2").withExpressionAttributeValues(eav);
		List<Announcements> result=mapper.scan(Announcements.class, scanExpression);
		if(result.size()!=0) {
			Announcements removedone=result.get(0);
			mapper.delete(removedone);
			return removedone;
		}
		return null;
	}
	
	//update
	public Announcements updateAnnouncement(String announcementId,String boardId,Announcements a) {
		deleteAnnouncement(a.getAnnouncementId(), a.getBoardId());
		mapper.save(a);
		return a;
	}
	
}
