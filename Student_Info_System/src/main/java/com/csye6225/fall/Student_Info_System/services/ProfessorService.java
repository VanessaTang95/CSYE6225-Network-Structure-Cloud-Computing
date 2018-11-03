package com.csye6225.fall.Student_Info_System.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.Professor;


public class ProfessorService {
	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProDB();
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list ;
	}

	// Adding a professor
	public void addProfessor(String name, Date joiningDate) {
		// Next Id 
		long nextAvailableId = prof_Map.size() + 1;
		
		//Create a Professor Object
		Professor prof = new Professor(name,joiningDate.toString());
		prof_Map.put(nextAvailableId, prof);
	}
	
	public Professor addProfessor(Professor prof) {	
		long nextAvailableId = prof_Map.size() + 1;
		prof.setPro_Id(nextAvailableId);
		prof_Map.put(nextAvailableId, prof);
		return prof_Map.get(nextAvailableId);
	}
	
	// Getting One Professor
	public Professor getProfessor(Long profId) {
		return prof_Map.get(profId);
	}
	
	// Deleting a professor
	public Professor deleteProfessor(Long profId) {
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(Long profId, Professor prof) {	
		Professor oldProfObject = prof_Map.get(profId);
		profId = oldProfObject.getPro_Id();
		prof.setPro_Id(profId);
		// Publishing New Values
		prof_Map.put(profId, prof) ;
		
		return prof;
	}
	
	// Get professors in one program/program
	public List<Professor> getProfessorsByProgram(String program) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if(prof.getBelongingProgram().contains(program)) {
				list.add(prof);
			}
		}
		return list ;
	}

}
