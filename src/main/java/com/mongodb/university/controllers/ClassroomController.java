package com.mongodb.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.university.models.Classroom;
import com.mongodb.university.models.Lesson;
import com.mongodb.university.models.Professor;
import com.mongodb.university.services.IClassroomService;



@RestController
@RequestMapping("/api/v2/classroom")
public class ClassroomController {
	
	private IClassroomService classroomService;

	@Autowired
	public ClassroomController(IClassroomService classroomService) {
		super();
		this.classroomService = classroomService;
	}
	
	@GetMapping
	public List<Classroom> getAllClassrooms(){
		return classroomService.getAllClassrooms();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Classroom> getClassroomById(@PathVariable("id") String id) throws Exception {
		return new ResponseEntity<Classroom>(classroomService.getClassroomById(id), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<Classroom> addNewClassroom(@RequestBody Classroom classroom){
		return new ResponseEntity<Classroom>(classroomService.addNewClassroom(classroom), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Classroom> updateClassroom(@PathVariable("id") String id, @RequestBody Classroom classroom)
			throws Exception {
		return new ResponseEntity<Classroom>(classroomService.updateClassroom(id, classroom), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClassroom(@PathVariable("id") String id) throws Exception{
		classroomService.deleteClassroom(id);
		return new ResponseEntity<String>("The classroom has deleted successfully!", HttpStatus.OK);
	}

}
