package com.mongodb.university.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.university.models.Lesson;

@Repository
public interface ILessonRepository extends MongoRepository<Lesson, Long>{
	
	Optional<Lesson> findById(String lesson_id);
	
	void deleteById(String id);

}
