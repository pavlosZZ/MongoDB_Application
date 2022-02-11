package com.mongodb.university.services;

import java.util.List;

import com.mongodb.university.models.Professor;




public interface IProfessorService {
	
	public List<Professor> getAllProfessors();

	public Professor addNewProfessor(Professor student);
	
	public Professor getProfessorById(String id) throws Exception;
	
	public Professor updateProfessor(Professor professor, String id) throws Exception;
	
	public void deleteProfessor(String id) throws Exception;
}
