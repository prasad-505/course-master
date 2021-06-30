package com.accolite.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

	List<StudentEntity> findByEmail(String email);
	
    @Query("SELECT email FROM StudentEntity")
    List<String> getAll();
    
    @Query("FROM StudentEntity")
    List<StudentEntity> getAll1();
    
   

}
