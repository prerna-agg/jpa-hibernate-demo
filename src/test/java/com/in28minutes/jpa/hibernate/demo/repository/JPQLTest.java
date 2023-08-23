package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


@SpringBootTest(classes=DemoApplication.class)
class JPQLTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	EntityManager em;
		
	@Test
	public void jpql_basic() {
		
		Query query = em.createQuery("Select c from Course c");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%50%'",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	

}
