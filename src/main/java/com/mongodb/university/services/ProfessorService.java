package com.mongodb.university.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.university.models.Professor;
import com.mongodb.university.models.Student;
import com.mongodb.university.repositories.IProfessorRepository;
import com.mongodb.university.repositories.IStudentRepository;

@Service
public class ProfessorService implements IProfessorService {

	private IProfessorRepository professorRepo;
	private IStudentRepository studentRepo;
	private NextSequenceService counter; // It's not in use.

	@Autowired
	public ProfessorService(IProfessorRepository professorRepo, IStudentRepository studentRepo, NextSequenceService counter) {
		super();
		this.professorRepo = professorRepo;
		this.studentRepo = studentRepo;
		this.counter = counter; 
	}

	@Override
	public List<Professor> getAllProfessors() {
		return professorRepo.findAll();
	}

	@Override
	public Professor addNewProfessor(Professor professor) {
//		professor.setId(counter.getNextSequence("Professor"));
		return professorRepo.save(professor);
	}

	@Override
	public Professor getProfessorById(String id) throws Exception {
		Optional<Professor> professor = professorRepo.findById(id);
		if (professor.isPresent()) {
			return professor.get();
		} else {
			throw new Exception();
		}
	}

	@Override
	public Professor updateProfessor(Professor professor, String id) throws Exception {
		if (professorRepo.findById(id) != null) {
			professor.setId(id);
			return professorRepo.save(professor);
		} else
			throw new Exception();

	}

	@Override
	public void deleteProfessor(String id) throws Exception {
		if (professorRepo.findById(id) != null) {
			boolean flag = false;
			List<Student> students = studentRepo.findAll();
			for(Student stud : students) {
				List<Professor> professors = stud.getProfessors();
				for(Professor prof : professors) {
					if(prof.getId().equals(id)) {
						professors.remove(prof);
						studentRepo.save(stud);
						flag = true;
						break;
					}
				}
				if(flag)
					break;
			}
			professorRepo.deleteById(id);
			
		}
		else
			throw new Exception();
	}

}
