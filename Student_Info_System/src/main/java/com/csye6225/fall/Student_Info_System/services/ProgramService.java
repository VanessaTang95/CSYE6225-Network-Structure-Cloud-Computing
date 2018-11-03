package com.csye6225.fall.Student_Info_System.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall.Student_Info_System.database.InMemoryDatabase;
import com.csye6225.fall.Student_Info_System.datamodel.Program;

public class ProgramService {
	static HashMap<Long,Program> program_Map=InMemoryDatabase.getProgramDB();

//getting a list of all programs
	public List<Program> getAllPrograms(){
		ArrayList<Program> list=new ArrayList<>();
		for(Program p:program_Map.values()) {
			list.add(p);
		}
		return list;
	}

//adding a Program
	public void addProgram(String programName) {
			//generate next id automatic
		long nextId=program_Map.size()+1;
		
		Program p=new Program(nextId,programName);
		program_Map.put(nextId, p);
	}
	
	public Program addProgram(Program p) {
		program_Map.put(p.getProgramId(), p);
		return p;
	}
	
	//getting one program
	public Program getOneProgram(long programId) {
		return program_Map.get(programId);
	}
	
	//deleting one program
	public Program deleteProgram(Long Id) {
		Program del=program_Map.get(Id);
		program_Map.remove(Id);
		return del;
	}
	
	//updating program
	public Program updateProgramInfo(Long id, Program p) {
		Program old=program_Map.get(id);
		id=old.getProgramId();
		program_Map.put(id, p);
		return p;
	}
	
	}
