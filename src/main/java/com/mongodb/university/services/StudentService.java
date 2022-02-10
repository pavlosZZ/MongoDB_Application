package com.mongodb.university.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.university.models.Lesson;
import com.mongodb.university.models.Professor;
import com.mongodb.university.models.Student;
import com.mongodb.university.repositories.ILessonRepository;
import com.mongodb.university.repositories.IProfessorRepository;
import com.mongodb.university.repositories.IStudentRepository;

@Service
public class StudentService implements IStudentService {

	private IStudentRepository studentRepo;

	private IProfessorRepository professorRepo;

	private ILessonRepository lessonRepo;
	private NextSequenceService counter;

	@Autowired
	public StudentService(IStudentRepository studentRepo, IProfessorRepository professorRepo,
			ILessonRepository lessonRepo, NextSequenceService counter) {
		super();
		this.studentRepo = studentRepo;
		this.professorRepo = professorRepo;
		this.lessonRepo = lessonRepo;
		this.counter = counter;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student addNewStudent(Student student) {
//		student.setId(counter.getNextSequence("Student"));
		student.setId(new ObjectId());
		return studentRepo.save(student);
	}

	@Override
	public void deleteStudent(long id) throws Exception {
		if (studentRepo.findById(id) != null)
			studentRepo.deleteById(id);
		else
			throw new Exception("There is no student with id:" + id);
	}

	@Override
	public Student updateStudent(Student student, long id) throws Exception {
		return student;

//		if (studentRepo.findById(id) != null) {
//			student.setId(id);
//
//			studentRepo.save(student);
//
//			return student;
//		} else {
//			throw new Exception();
//		}

	}

	@Override
	public Student getStudentById(long id) throws Exception {
		Optional<Student> student = studentRepo.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else {
			throw new Exception();
		}
	}

	@Override
	public Student updateProfessorsList(long stud_id, long prof_id) {
		Student student = studentRepo.findById(stud_id).get();
		Professor professor = professorRepo.findById(prof_id).get();
		student.enrolledProfessor(professor);
		return studentRepo.save(student);
	}

	@Override
	public Student updateStudentsLesson(long stud_id, long lesson_id) {
		Student student = studentRepo.findById(stud_id).get();
		Lesson lesson = lessonRepo.findById(lesson_id).get();
		student.setLesson(lesson);
		return studentRepo.save(student);
	}

}
