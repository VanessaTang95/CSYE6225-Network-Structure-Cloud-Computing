package amazonaws.lambda.demo1;

import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;

public class LambdaInvokeStepFunction implements RequestHandler<DynamodbEvent, String> {

	DynamoDBMapper mapper;
	private AmazonDynamoDB db=
			AmazonDynamoDBClientBuilder
			.standard()
			.withRegion(Regions.US_WEST_2)
			.withCredentials(new EnvironmentVariableCredentialsProvider())
			.build();
	
	private AWSStepFunctions sf=AWSStepFunctionsClientBuilder
			.standard()
			.withCredentials(new EnvironmentVariableCredentialsProvider())
			.withRegion(Regions.US_WEST_2)
			.build();
	
	
    @Override
    public String handleRequest(DynamodbEvent event, Context context) {
        context.getLogger().log("Received event: " + event);
        
        for(DynamodbStreamRecord record : event.getRecords()) {
        	if(record.getEventName().equals("INSERT")) {
        		Map<String,AttributeValue> map=record.getDynamodb().getNewImage();
        		System.out.println("Find bug!");
        		Item item=new Item()
        				.withString("Id", map.get("Id").getS())
        				.withString("courseId", map.get("courseId").getS())
        				.withString("courseName", map.get("courseName").getS())
        				.withString("boardId", "")
        				.withString("department", map.get("department").getS())
        				.withString("professorId", map.get("professorId").getS())
        				.withString("notificationTopic", "")
        				.withString("taId", map.get("taId").getS())
        				.withString("roster", "");
        		
        		//convert item to json string
        		String target=item.toJSON();
        		
        		//start step function
        		StartExecutionRequest startExecutionRequest=new StartExecutionRequest()
        				.withInput(target)
        				.withStateMachineArn("arn:aws:states:us-west-2:883728755608:stateMachine:Assignment4");
        		StartExecutionResult executionResult=sf.startExecution(startExecutionRequest);
        	}
        	
        }
        return event.getRecords()
        		.size()+", trigger successful";
        }
    
    
    
    }
