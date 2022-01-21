package com.mongodb.university.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Classroom")
public class Classroom {
	
	@Id
	private long id;
	
	private String class_name;
	@JsonBackReference
    private Lesson lesson;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	
	
	
	

	
}
