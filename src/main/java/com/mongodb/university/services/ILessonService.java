package com.mongodb.university.services;

import java.util.List;

import com.mongodb.university.models.Lesson;



public interface ILessonService {
	
	public List<Lesson> getAllLessons();
	
	public Lesson addNewLesson(Lesson lesson);
	
	public void deleteLesoon(long id) throws Exception;
	
	public Lesson updateLessonsClassroom(long lesson_id, long classroom_id);
	
	public Lesson getLessonById(long id) throws Exception;

}
