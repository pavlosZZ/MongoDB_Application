package com.mongodb.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mongodb.university.models.Student;
import com.mongodb.university.services.IStudentService;

@RestController
@RequestMapping("/api/v2/student")
public class StudentController {

	private IStudentService studentService;

	@Autowired
	public StudentController(IStudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getEmployeeById(@PathVariable("id") long id) throws Exception {
		return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.addNewStudent(student), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") long id)
			throws Exception {
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) throws Exception {
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("Student has deleted successfully!", HttpStatus.OK);
	}

	@PutMapping("/{stud_id}/professor/{prof_id}")
	public ResponseEntity<Student> updateProfessorsList(@PathVariable("stud_id") long stud_id,
			@PathVariable("prof_id") long prof_id) {
		return new ResponseEntity<Student>(studentService.updateProfessorsList(stud_id, prof_id), HttpStatus.OK);

	}

	@PutMapping("/{stud_id}/lesson/{lesson_id}")
	public ResponseEntity<Student> updateStudentsLesson(@PathVariable("stud_id") long stud_id,
			@PathVariable("lesson_id") long lesson_id) {
		return new ResponseEntity<Student>(studentService.updateStudentsLesson(stud_id, lesson_id), HttpStatus.OK);
	}

}
