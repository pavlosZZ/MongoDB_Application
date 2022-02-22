package com.mongodb.university.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.university.models.Classroom;
import com.mongodb.university.models.Lesson;
import com.mongodb.university.models.Professor;
import com.mongodb.university.repositories.IClassroomRepository;
import com.mongodb.university.repositories.ILessonRepository;

@Service
public class LessonService implements ILessonService {

	private ILessonRepository lessonRepo;

	private IClassroomRepository classroomRepo;
	private NextSequenceService counter; // It's not in use.

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
	public Lesson addNewLesson(Lesson lesson) throws Exception {
//		lesson.setId(counter.getNextSequence("Lesson"));
		List<Lesson> lessons = lessonRepo.findAll();
		Classroom new_classroom = lesson.getClassroom();
		if (new_classroom == null)
			return lessonRepo.save(lesson);
		else if (new_classroom.getId() == null)
			classroomRepo.save(new_classroom);
		else if (new_classroom.getId() != null) {
			String check_id = new_classroom.getId();
			for (Lesson element : lessons) {
				Classroom check_classroom = element.getClassroom();
				String element_id = check_classroom.getId();
				if (check_id == element_id) {
					throw new Exception("Classroom with id :" + check_id + "is already used.");
				}
			}
			new_classroom.setId(check_id);
			classroomRepo.save(new_classroom);
		}
		return lessonRepo.save(lesson);
	}

	@Override
	public void deleteLesoon(String id) throws Exception {
		if (lessonRepo.findById(id) != null)
			lessonRepo.deleteById(id);
		else
			throw new Exception();

	}

	@Override
	public Lesson updateLessonsClassroom(String lesson_id, String classroom_id) {
		Lesson lesson = lessonRepo.findById(lesson_id).get();
		Classroom classroom = classroomRepo.findById(classroom_id).get();
		lesson.setClassroom(classroom);
		return lessonRepo.save(lesson);
	}

	public Lesson getLessonById(String id) throws Exception {
		Lesson lesson = lessonRepo.findById(id).get();
		if (lesson != null)
			return lesson;
		else
			throw new Exception();
	}

	@Override
	public Lesson updateLesson(String id, Lesson lesson) throws Exception {
		if (lesson != null) {
			Classroom new_classroom = lesson.getClassroom();
			if (new_classroom == null) {
				lesson.setId(id);
				return lessonRepo.save(lesson);
			}
			else if (new_classroom.getId() == null)
				classroomRepo.save(new_classroom);
			else if(new_classroom.getId() != null){
				String check_id = new_classroom.getId();
				List<Lesson> lessons = lessonRepo.findAll();
				for(Lesson element : lessons) {
					Classroom lesson_classroom = element.getClassroom();
					if (lesson_classroom == null)
						continue;
					String element_id = lesson_classroom.getId();
					if (check_id == element_id) {
						throw new Exception("Classroom with id :" + check_id + "is already used.");
					}				
				}
			}
			lesson.setId(id);
			return lessonRepo.save(lesson);

		} else
			throw new Exception();

	}

}
