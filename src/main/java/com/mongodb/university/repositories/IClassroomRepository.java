package com.mongodb.university.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.university.models.Classroom;

@Repository
public interface IClassroomRepository extends MongoRepository<Classroom, Long>{

}
