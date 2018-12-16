package amazonaws.lambda.demo3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall.Student_Info_System.datamodel.Board;
import com.csye6225.fall.Student_Info_System.datamodel.Course;
import com.csye6225.fall.Student_Info_System.services.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegisterBoard implements RequestHandler<Object, Object> {

	private static AmazonDynamoDB dynamoDB;
	private static DynamoDBMapper mapper;
	
    @Override
    public Object handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        
        //init dynamodb
        if(dynamoDB==null) {
        	dynamoDB=AmazonDynamoDBClientBuilder
        			.standard()
        			.withCredentials(new EnvironmentVariableCredentialsProvider())
        			.withRegion(Regions.US_WEST_2)
        			.build();
        	mapper=new DynamoDBMapper(dynamoDB);
        }
        
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> map=objectMapper.convertValue(input, Map.class);
        String courseId=map.get("courseId").toString();
        
        Board board=new Board();
        board.setCourseId(courseId);
        DynamoDBScanExpression scanExpression=new DynamoDBScanExpression();
        List<Board> item=mapper.scan(Board.class, scanExpression);
        long temp=item.size()+1;
        String boardId=String.valueOf(temp);
        board.setBoardId(boardId);
        mapper.save(board);
        
        //update course
        Map<String,AttributeValue> eav=new HashMap<>();
        eav.put(":val", new AttributeValue().withS(courseId));
        DynamoDBScanExpression scanExpression2=new DynamoDBScanExpression()
        		.withFilterExpression("courseId=:val")
        		.withExpressionAttributeValues(eav);
        List<Course> item2=mapper.scan(Course.class, scanExpression2);
        Course course=item2.get(0);
        course.setBoardId(boardId);
        mapper.save(course);
        
        
        
        return input;
    }

}
