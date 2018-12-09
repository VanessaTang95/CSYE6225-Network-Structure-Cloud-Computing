package amazonaws.lambda.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.csye6225.fall.Student_Info_System.datamodel.Board;
import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.services.BoardService;
import com.csye6225.fall.Student_Info_System.services.CourseService;
import com.csye6225.fall.Student_Info_System.services.RegisterService;

public class LambdaFunction implements RequestHandler<DynamodbEvent, String> {
	//build sns
		private static AmazonSNS sns=AmazonSNSClientBuilder
									.standard()
									.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
									.withRegion(Regions.US_WEST_2)
									.build();
		
		private static AmazonDynamoDB db=AmazonDynamoDBClientBuilder
				.standard()
				.withRegion(Regions.US_WEST_2)
				.withCredentials(new EnvironmentVariableCredentialsProvider())
				.build();
	
	@Override
	public String handleRequest(DynamodbEvent event, Context context) {
		System.out.println("Start to trigger.");
		
		if(event.getRecords()!=null) {
			for(DynamodbStreamRecord record:event.getRecords()) {
				if(record==null) {
					continue;
				}
				
				//find boardId, then use boardId to get courseId 
				Map<String, AttributeValue> item=record.getDynamodb().getNewImage();
				String boardId=item.get("boardId").getS();
				String announcementText=item.get("announcementText").getS();
				System.out.println("BoardId:"+boardId+", announcementText:"+announcementText);
				
				Board board=new BoardService().getOneBoard(boardId);
				String courseId=board.getCourseId();
				System.out.println("CourseId:"+courseId);
				
				Course course=new CourseService().getOneCourse(courseId);
				
				
				//find topicArn using courseId
				String topicArn="";
				Map<String, AttributeValue> item2=new HashMap<>();
				item2.put(":val", new AttributeValue().withS(courseId));
				ScanRequest scanRequest=new ScanRequest()
						.withTableName("Course")
						.withFilterExpression("courseId=:val")
						.withExpressionAttributeValues(item2);
				ScanResult scanResult=db.scan(scanRequest);
				List<Map<String,AttributeValue>> result=scanResult.getItems();
				for(Map<String,AttributeValue>i:result) {
					if(i!=null) {
						topicArn=i.get("notificationTopic").getS();
					}
				}
				
				context.getLogger().log(courseId);
				context.getLogger().log(topicArn);
				
				String message="A new annoucement has been published for course "+courseId;
				context.getLogger().log(message);
				
				//publish to sns topic
				PublishRequest publishRequest=new PublishRequest()
						.withMessage(message)
						.withSubject("Course "+courseId+" Announcement")
						.withTargetArn(topicArn);
				
				PublishResult publishResult=sns.publish(publishRequest);
				
			}
		}
		
		return "Successfully sent records: "+event.getRecords().size();
	}
	
	
	
	

}