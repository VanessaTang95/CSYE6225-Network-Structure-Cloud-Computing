package com.csye6225.fall.Student_Info_System.services;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.Attribute;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.Board;
import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;
import com.csye6225.fall.Student_Info_System.datamodel.SNSConnector;

//import amazonaws.lambda.demo.LambdaFunction;

	public class CourseService {
	//	static HashMap<Long,Course> course_Map=InMemoryDatabase.getCourseDB();
		static DynamoDBConnector dynamoDb;
		DynamoDBMapper mapper;
		
		BoardService boardService=new BoardService();
		static SNSConnector sns;
		AmazonSNS snsClient;
		
		public CourseService() {
			dynamoDb=new DynamoDBConnector();
			dynamoDb.init();
			mapper=new DynamoDBMapper(dynamoDb.getClient());
			
			sns=new SNSConnector();
			sns.init();
			snsClient=sns.getClient();
		}
		
		//getting course by courseId
				public Course getOneCourse(String courseId) {
					List<Course> courses=mapper.scan(Course.class, new DynamoDBScanExpression());
					Course target=new Course();
					for(Course c:courses) {
						if(c.getCourseId().equals(courseId)){
							target=c;
						}
					}
					return target;
				}
				
				
		//getting course by Id
				public Course getCourseById(String Id) {
					return mapper.load(Course.class,Id);
				}
				
				
		////get courses by a department
				public List<Course> getCourseByDepartment(String department){
					List<Course> list=mapper.scan(Course.class, new DynamoDBScanExpression());
					List<Course> target=new ArrayList<>();
					for(Course c:list) {
						if(c.getDepartment().equals(department))
							target.add(c);
					}
					return target;
				}
		
	//getting a list of all courses
		public List<Course> getAllCourse(){
			List<Course> courses=mapper.scan(Course.class, new DynamoDBScanExpression());
			return courses;
		}

	//getting courses by professor by using expression attribute value
		public List<Course> getCourseByProfessor(String professorId){
			Map<String,AttributeValue> eav=new HashMap<>();
			eav.put(":val", new AttributeValue().withS(professorId));
			
			DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
					.withFilterExpression("professorId=:val").withExpressionAttributeValues(eav);
			List<Course> target=mapper.scan(Course.class,scanExpression);
			return target;
		}
		
		
		
	//adding a course
		public void addCourse(String courseId, String courseName, String professorId, String taId, String department
				) /*throws Exception*/ {
			/*Map<String,AttributeValue> eav=new HashMap<>();
			eav.put(":val", new AttributeValue().withS(courseId));
			DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
					.withFilterExpression("courseId=:val").withExpressionAttributeValues(eav);
			List<Course> courseItems=mapper.scan(Course.class, scanExpression);
			if(courseItems.size()!=0) {
				throw new Exception("This course already exists!");
			}
			else {*/
			DynamoDBScanExpression dbScanExpression=new DynamoDBScanExpression();			
			List<Board> boardItems=mapper.scan(Board.class, dbScanExpression);			
			String boardId=String.valueOf(boardItems.size()+1);
			String rosterId=String.valueOf(new CourseService().getOneCourse(courseId).getRoster().size()+1);
			Course cur_course=new Course(courseId,courseName,professorId,taId,department);
			cur_course.getRoster().add(rosterId);
			boardService.addBoard(boardId, courseId);
			CreateTopicRequest createTopicRequest=new CreateTopicRequest(courseId);
			CreateTopicResult createTopicResult=snsClient.createTopic(createTopicRequest);
			cur_course.setNotificationTopic(createTopicResult.getTopicArn());
			mapper.save(cur_course);
			//}
		}
		
		
		public Course addCourse(Course course)/* throws Exception*/ {

		/*	String courseId=course.getCourseId();

			Map<String,AttributeValue> eav=new HashMap<>();
			eav.put(":val", new AttributeValue().withS(courseId));
			DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
					.withFilterExpression("courseId=:val").withExpressionAttributeValues(eav);
			List<Course> courseItems=mapper.scan(Course.class, scanExpression);
			if(courseItems.size()!=0) {
				throw new Exception("This course already exists!");
				
			}else {
*/			CreateTopicRequest createTopicRequest=new CreateTopicRequest(course.getCourseId());
			CreateTopicResult createTopicResult=snsClient.createTopic(createTopicRequest);
			course.setNotificationTopic(createTopicResult.getTopicArn());
			mapper.save(course);
			
			return mapper.load(Course.class,course.getId());
		}
		
		
		//deleting one course
		public Course deleteCourse(String courseId) {
			List<Course> list=mapper.scan(Course.class, new DynamoDBScanExpression());
			Course target=new Course();
			for(Course c:list) {
				if(c.getCourseId().equals(courseId)) {
					target=c;
					mapper.delete(c);
				}
			}
			return target;
		}
		
		//updating Course 
		public Course updateCourseInfo(String courseId, Course course) {
			List<Course> list=mapper.scan(Course.class, new DynamoDBScanExpression());
			for(Course c:list) {
				if(c.getCourseId().equals(courseId)) {
					String Id=c.getId();
					course.setId(Id);
					mapper.save(course);
					return mapper.load(Course.class,course.getId());
				}
			}
			return null;
		}
		
		
		
	}

