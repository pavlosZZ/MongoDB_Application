package com.mongodb.university.services;

import java.util.List;

import com.mongodb.university.models.Lesson;



public interface ILessonService {
	
	public List<Lesson> getAllLessons();
	
	public Lesson addNewLesson(Lesson lesson) throws Exception;
	
	public Lesson updateLesson(String id, Lesson lesson) throws Exception;
	
	public void deleteLesoon(String id) throws Exception;
	
	public Lesson updateLessonsClassroom(String lesson_id, String classroom_id);
	
	public Lesson getLessonById(String id) throws Exception;

}
