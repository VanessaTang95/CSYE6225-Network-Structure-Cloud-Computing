package com.csye6225.fall.Student_Info_System.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Professors")
public class Professor {
	private String Id;
	private String professorId;
	private String firstName;
	private String LastName;
	private String joiningDate;
	private String department;
	
	public Professor() {
	
	}
	
	public Professor(String firstName,String lastName, String joiningDate, String department ) {
		this.firstName=firstName;
		this.LastName=lastName;
		this.joiningDate=joiningDate;
		this.department=department;
	}

	@DynamoDBHashKey(attributeName="Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@DynamoDBIndexHashKey(attributeName="ProfessorId",globalSecondaryIndexName="Professor_ProfessorId")
	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	@DynamoDBAttribute(attributeName="FirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute(attributeName="LastName")
	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	@DynamoDBAttribute(attributeName="JoiningDate")
	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	@DynamoDBAttribute(attributeName="Department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@DynamoDBIgnore	
	@Override
	public String toString() {
	return "ProfessorId:"+getProfessorId()+", Name:"+getLastName()+" "+getFirstName()
	+", Department:"+getDepartment()+", Joining Date:"+getJoiningDate();
	}

}
