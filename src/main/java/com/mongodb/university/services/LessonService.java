package com.mongodb.university.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.university.models.Classroom;
import com.mongodb.university.models.Lesson;
import com.mongodb.university.repositories.IClassroomRepository;
import com.mongodb.university.repositories.ILessonRepository;

@Service
public class LessonService implements ILessonService {

	private ILessonRepository lessonRepo;

	private IClassroomRepository classroomRepo;
	private NextSequenceService counter;

	@Autowired
	public LessonService(ILessonRepository lessonRepo, IClassroomRepository classroomRepo,
			NextSequenceService counter) {
		super();
		this.lessonRepo = lessonRepo;
		this.classroomRepo = classroomRepo;
		this.counter = counter;
	}

	@Override
	public List<Lesson> getAllLessons() {
		return lessonRepo.findAll();
	}

	@Override
	public Lesson addNewLesson(Lesson lesson) {
		lesson.setId(counter.getNextSequence("Lesson"));
		return lessonRepo.save(lesson);
	}

	@Override
	public void deleteLesoon(long id) throws Exception {
		if (lessonRepo.findById(id) != null)
			lessonRepo.deleteById(id);
		else
			throw new Exception();

	}

	@Override
	public Lesson updateLessonsClassroom(long lesson_id, long classroom_id) {
		Lesson lesson = lessonRepo.findById(lesson_id).get();
		Classroom classroom = classroomRepo.findById(classroom_id).get();
		lesson.setClassroom(classroom);
		return lessonRepo.save(lesson);
	}

	public Lesson getLessonById(long id) throws Exception {
		Lesson lesson = lessonRepo.findById(id).get();
		if (lesson != null)
			return lesson;
		else
			throw new Exception();
	}

}
