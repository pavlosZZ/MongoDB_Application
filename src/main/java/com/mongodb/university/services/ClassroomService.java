package com.mongodb.university.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.university.models.Classroom;
import com.mongodb.university.repositories.IClassroomRepository;

@Service
public class ClassroomService implements IClassroomService {

	private IClassroomRepository classroomRepo;
	private NextSequenceService counter;

	@Autowired
	public ClassroomService(IClassroomRepository classroomRepo, NextSequenceService counter) {
		super();
		this.classroomRepo = classroomRepo;
		this.counter = counter;
	}

	@Override
	public List<Classroom> getAllClassrooms() {
		return classroomRepo.findAll();
	}

	@Override
	public Classroom addNewClassroom(Classroom classroom) {
//		classroom.setId(counter.getNextSequence("Classroom"));
		return classroomRepo.save(classroom);
	}

	@Override
	public void deleteClassroom(String id) throws Exception {
		if (classroomRepo.findById(id) != null)
			classroomRepo.deleteById(id);
		else
			throw new Exception();

	}

	public Classroom getClassroomById(String id) throws Exception {
		Classroom classroom = classroomRepo.findById(id).get();
		if (classroom != null)
			return classroom;
		else
			throw new Exception();
	}

	@Override
	public Classroom updateClassroom(String id, Classroom classroom) throws Exception {
		if (classroomRepo.findById(id) != null) {
			classroom.setId(id);
			return classroomRepo.save(classroom);
		} else
			throw new Exception();
	}

}
