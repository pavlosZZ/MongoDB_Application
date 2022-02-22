package com.mongodb.university.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.university.models.Lesson;
import com.mongodb.university.models.Professor;
import com.mongodb.university.models.Student;
import com.mongodb.university.repositories.IClassroomRepository;
import com.mongodb.university.repositories.ILessonRepository;
import com.mongodb.university.repositories.IProfessorRepository;
import com.mongodb.university.repositories.IStudentRepository;

@Service
public class StudentService implements IStudentService {

	private IStudentRepository studentRepo;

	private IProfessorRepository professorRepo;

	private ILessonRepository lessonRepo;
	private IClassroomRepository classroomRepo;
	private NextSequenceService counter; // It's not in use.

	@Autowired
	public StudentService(IStudentRepository studentRepo, IProfessorRepository professorRepo,
			ILessonRepository lessonRepo, IClassroomRepository classroomRepo, NextSequenceService counter) {
		super();
		this.studentRepo = studentRepo;
		this.professorRepo = professorRepo;
		this.lessonRepo = lessonRepo;
		this.classroomRepo = classroomRepo;
		this.counter = counter;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student addNewStudent(Student student) {
//		student.setId(counter.getNextSequence("Student"));
//		student.setId(String.valueOf(counter.getNextSequence("Student")));
		List<Professor> new_professors = student.getProfessors();
		for (int i = 0; i < new_professors.size(); i++) {
			Professor prof = new_professors.get(i);
			if (prof.getId() == null)
				professorRepo.save(prof);
			else {
				prof.setId(prof.getId());
				professorRepo.save(prof);
			}

		}
//		new_professors.stream().filter((p) -> p.getLastName().equals()).forEach((p) -> professorRepo.save(p));
//		student.setProfessors(new_professors);
		return studentRepo.save(student);
	}

	@Override
	public void deleteStudent(String id) throws Exception {
		if (studentRepo.findById(id) != null)
			studentRepo.deleteById(id);
		else
			throw new Exception("There is no student with id:" + id);
	}

	@Override
	public Student updateStudent(Student student, String id) throws Exception {

		if (studentRepo.findById(id) != null) {
			List<Professor> professors = student.getProfessors();
			Lesson lesson = student.getLesson();
			for (Professor prof : professors) {
				if (prof.getId() == null)
					professorRepo.save(prof);
			}
			if (lesson != null) {
				if (lesson.getId() == null) {
					lessonRepo.save(lesson);
					if(lesson.getClassroom() != null) {
						if (lesson.getClassroom().getId() == null)
							classroomRepo.save(lesson.getClassroom());
					}
				} else {
					if(lesson.getClassroom() != null) {
						if (lesson.getClassroom().getId() == null)
							classroomRepo.save(lesson.getClassroom());
					}
				}

			}
			student.setId(id);
			studentRepo.save(student);
			return student;
		} else {
			throw new Exception();
		}

	}

	@Override
	public Student getStudentById(String id) throws Exception {
		Optional<Student> student = studentRepo.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else {
			throw new Exception();
		}
	}

	@Override
	public Student updateProfessorsList(String stud_id, String prof_id) {
		Student student = studentRepo.findById(stud_id).get();
		Professor professor = professorRepo.findById(prof_id).get();
		student.enrolledProfessor(professor);
		return studentRepo.save(student);
	}

	@Override
	public Student updateStudentsLesson(String stud_id, String lesson_id) {
		Student student = studentRepo.findById(stud_id).get();
		Lesson lesson = lessonRepo.findById(lesson_id).get();
		student.setLesson(lesson);
		return studentRepo.save(student);
	}

}
