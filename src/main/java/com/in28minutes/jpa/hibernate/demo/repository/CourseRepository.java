package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public Course save(Course course) {
		//insert or update
		if(course.getId()==null) {
			em.persist(course);
		}
		else {
			em.merge(course);
		}
		return course;
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public void playWithEntityManager() {
		
		Course course1 = new Course("WebServices in 100 Steps");
		em.persist(course1);
		Course course2 = new Course("AngularJS in 100 Steps");
		em.persist(course2);
		em.flush();
		
//		em.detach(course1);
//		em.detach(course2);		
//		em.clear();
		
		course1.setName("WebServices in 100 Steps - Updated");
		course2.setName("AngularJS in 100 Steps - Updated");
		em.refresh(course1);	
	}
	
	public void playWithEntityManagerAndNativeQuery() {
		Query query = em.createNativeQuery("SELECT * from Course where id = 10001", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * from Course -> {}", resultList);
		resultList.get(0).setName("JPA in 50 Steps - Updated");
		
	}
}
