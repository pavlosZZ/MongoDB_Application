package com.mongodb.university.services;

import java.util.List;

import com.mongodb.university.models.Student;



public interface IStudentService {
	
	public List<Student> getAllStudents();
	
	public Student getStudentById(String id) throws Exception;
	
	public Student addNewStudent(Student student);
	
	public Student updateStudent(Student student, String id) throws Exception;
	
	public void deleteStudent(String id) throws Exception;
	
	public Student updateProfessorsList(String stud_id, String prof_id);
	
	public Student updateStudentsLesson(String stud_id, String lesson_id);

}
