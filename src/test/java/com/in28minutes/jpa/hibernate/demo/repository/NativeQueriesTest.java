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
import jakarta.transaction.Transactional;


@SpringBootTest(classes=DemoApplication.class)
class NativeQueriesTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	EntityManager em;
		
	@Test
	public void native_query_basic() {
		
		Query query = em.createNativeQuery("SELECT * from Course", Course.class);
		List resultList = query.getResultList();
		logger.info("SELECT * from Course -> {}", resultList);
	}
	
	@Test
	public void native_query_with_parameter() {
		
		Query query = em.createNativeQuery("SELECT * from Course where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * from Course -> {}", resultList);
	}
	
	@Test
	public void native_query_with_named_parameter() {
		
		Query query = em.createNativeQuery("SELECT * from Course where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * from Course -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void native_query_to_update() {
		Query query = em.createNativeQuery("Update COURSE set last_updated_date = CURRENT_TIMESTAMP()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		//no persistence context is present, refresh to get the latest value from database.
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}
	
}
