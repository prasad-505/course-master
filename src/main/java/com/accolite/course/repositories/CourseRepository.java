package com.accolite.course.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.accolite.course.entities.CourseEntity;



@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {

	
	 //   @Query("FROM CourseEntity WHERE location = ?1")
	    List<CourseEntity> findByLocation(String location);
	    
	    @Query("FROM CourseEntity WHERE id=?1")
	    CourseEntity findById1(Long id);	 
}
