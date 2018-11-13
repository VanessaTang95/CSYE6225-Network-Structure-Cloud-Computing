package com.csye6225.fall.Student_Info_System.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.DynamoDBConnector;
import com.csye6225.fall.Student_Info_System.datamodel.Student;

public class StudentService {
//	static HashMap<Long,Student> stu_Map=InMemoryDatabase.getStuDB();
	static DynamoDBConnector dynamoDB;
	static DynamoDBMapper mapper;
	
	public StudentService() {
		dynamoDB=new DynamoDBConnector();
		dynamoDB.init();
		mapper=new DynamoDBMapper(dynamoDB.getClient());
	}
	
	
//getting a list of all students
	public List<Student> getAllStudents(){
		return mapper.scan(Student.class, new DynamoDBScanExpression());
	}

	
	//getting one student
	public Student getStudent(String studentId) {
		List<Student> list=mapper.scan(Student.class,new DynamoDBScanExpression());
		for(Student s:list) {
			if(s.getStudentId().equals(studentId)) {
				return s;
			}
		}
		return null;
		
	}

	
//adding a student
	public void addStudent(String studentId,String firstName, String lastName, String joiningDate, String department) {
		Student s=new Student(studentId,firstName,lastName,joiningDate,department);
		mapper.save(s);
	}
	
	public Student addStudent(Student s) {
		mapper.save(s);
		return mapper.load(Student.class,s.getId());
	}
	
	
	//deleting one student
	public Student deleteStudent(String stuId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val", new AttributeValue().withS(stuId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("studentId=:val").withExpressionAttributeValues(eav);
		List<Student> target=mapper.scan(Student.class, scanExpression);//get the list after filter
if(target.size()!=0) {
			Student removedStudent=target.get(0);
			mapper.delete(removedStudent);
			return removedStudent;
		}
		return null;
		
	}
		
	//updating Student id
	public Student updateStuInfo(String studentId, Student s) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val", new AttributeValue().withS(studentId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("studentId=:val").withExpressionAttributeValues(eav);
		List<Student> target=mapper.scan(Student.class, scanExpression);//get the list after filter
		if(target.size()!=0) {
			String Id=target.get(0).getId();
			s.setId(Id);
			mapper.save(s);
			return mapper.load(Student.class,Id);	
		}
		return null;
	}
	
	//get students in same course
	public List<Student> getStudentByCourse(String course){
		List<Student> list=mapper.scan(Student.class, new DynamoDBScanExpression());
		List<Student> result=new ArrayList<>();
		for(Student s:list) {
			if(s.getRegisteredCourses().contains(course)) {
				result.add(s);
			}
		}
		return result;
	}
	
	//get students in a department
	public List<Student> getStudentByDepartment(String department){
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val", new AttributeValue().withS(department));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("department=:val").withExpressionAttributeValues(eav);
		List<Student> target=mapper.scan(Student.class, scanExpression);//get the list after filter
		return target;
	}
}
