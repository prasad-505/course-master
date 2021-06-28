package com.accolite.course.repositories;

import org.springframework.data.repository.CrudRepository;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

}
