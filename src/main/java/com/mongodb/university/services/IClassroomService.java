package com.mongodb.university.services;

import java.util.List;

import com.mongodb.university.models.Classroom;




public interface IClassroomService {
	
	public List<Classroom> getAllClassrooms();
	
	public Classroom getClassroomById(long id) throws Exception;
	
	public Classroom addNewClassroom(Classroom classroom);
	
	public void deleteClassroom(long id) throws Exception;

}
