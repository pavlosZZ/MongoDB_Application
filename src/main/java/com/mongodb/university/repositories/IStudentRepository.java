package com.mongodb.university.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.university.models.Student;

@Repository
public interface IStudentRepository extends MongoRepository<Student, Long> {

}
