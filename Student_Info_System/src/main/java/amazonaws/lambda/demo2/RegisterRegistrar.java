package amazonaws.lambda.demo2;

import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall.Student_Info_System.datamodel.Registrar;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegisterRegistrar implements RequestHandler<Object, Object> {
	private static AmazonDynamoDB dynamoDB;
	private static DynamoDBMapper mapper;
	
    @Override
    public Object handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

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
        String department=map.get("department").toString();
        String registrationId="Registration_"+courseId;
        String offeringId=courseId;
        String offeringType="Course";
        int perUnitPrice=1500;
        
        Registrar newregis=new Registrar(registrationId,offeringId,offeringType,department,perUnitPrice);
/*        newregis.setDepartment(department);
        newregis.setOfferingId(offeringId);
        newregis.setOfferType(offeringType);
        newregis.setPerUnitPrice(perUnitPrice);
        newregis.setRegistrationId(registrationId);
*/        
        mapper.save(newregis);
        return input;
        
         }

}
