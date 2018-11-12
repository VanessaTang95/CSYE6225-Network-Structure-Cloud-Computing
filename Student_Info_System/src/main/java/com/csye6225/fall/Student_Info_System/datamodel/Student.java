package com.csye6225.fall.Student_Info_System.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Student")
public class Student {
	private String Id, studentId, firstName, lastName, joiningDate, department, imageUrl;
	private List<String> registeredCourses;//has list of registered courseIds
	
	public Student() {
		
	}
	
	public Student(String firstName, String lastName, String joiningDate, String department) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.joiningDate=joiningDate;
		this.department=department;
	}

	
	public Student(String studentId, String firstName, String lastName, String joiningDate, String department,
			String imageUrl, List<String> registeredCourses) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joiningDate = joiningDate;
		this.department = department;
		this.imageUrl = imageUrl;
		this.registeredCourses = registeredCourses;
	}

	@DynamoDBHashKey(attributeName="Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@DynamoDBIndexHashKey(attributeName="studentId",globalSecondaryIndexName="Student_StudentId")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@DynamoDBAttribute(attributeName="firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute(attributeName="lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDBAttribute(attributeName="joiningDate")
	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName="registeredCourses")
	public List<String> getRegisteredCourses() {
		return registeredCourses;
	}

	@DynamoDBAttribute(attributeName="imageUrl")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setRegisteredCourses(List<String> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "StudentId:"+getStudentId()+", Name:"+getLastName()+" "+getFirstName()
		+", Department:"+getDepartment()+", Joining Date:"+getJoiningDate()+", Regiester Course:"
		+getRegisteredCourses()+", ImageUrl:"+getImageUrl();
	}
	
}
