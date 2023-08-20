package com.in28minutes.jpa.hibernate.demo.repository;

import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CourseRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
}
