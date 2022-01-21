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

import com.mongodb.university.models.Professor;
import com.mongodb.university.services.IProfessorService;

@RestController
@RequestMapping("/api/v2/professor")
public class ProfessorController {

	private IProfessorService professorService;

	@Autowired
	public ProfessorController(IProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@GetMapping
	public List<Professor> getAllProfessors() {
		return professorService.getAllProfessors();
	}

	@PostMapping
	public ResponseEntity<Professor> addNewProfessor(@RequestBody Professor professor) {
		return new ResponseEntity<Professor>(professorService.addNewProfessor(professor), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable("id") long id) throws Exception {
		return new ResponseEntity<Professor>(professorService.getProfessorById(id), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Professor> updateProfessor(@PathVariable("id") long id, @RequestBody Professor professor)
			throws Exception {
		return new ResponseEntity<Professor>(professorService.updateProfessor(professor, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProfessor(@PathVariable("id") long id) throws Exception {
		professorService.deleteProfessor(id);
		return new ResponseEntity<String>("Professor has deleted successfully!", HttpStatus.OK);

	}

}
