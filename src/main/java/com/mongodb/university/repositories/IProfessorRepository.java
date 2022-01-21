package com.mongodb.university.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.university.models.Professor;

@Repository
public interface IProfessorRepository extends MongoRepository<Professor, Long>{

}
