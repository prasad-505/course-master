package com.accolite.course.controller;

import java.util.List;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.models.Student;
import com.accolite.course.models.Mail;
import com.accolite.course.repositories.CourseRepository;
import com.accolite.course.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;
	
	
	
	@GetMapping("/mail/{to}/{body}/{sub}")
	public void send(@PathVariable("to") String to,@PathVariable("body") String body,@PathVariable("sub") String sub)
	{
		courseService.sendmail(to,body,sub);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Course> savethedata(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.saveIntocourseItemTable(course), HttpStatus.OK);
	}
	
	@PostMapping("/student/save1")
	public ResponseEntity<Student> savethedata(@RequestBody Student course) {
		return new ResponseEntity<>(courseService.saveIntocourseItemTable(course), HttpStatus.OK);
	}
	

	@GetMapping(path = "/{id}")
	public ResponseEntity<Course> fetchid(@PathVariable("id") Long id) {
		Course courseData = null;
		try {
			courseData = courseService.fetchRecordFromcourseTable(id);
		} catch (NoContentException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(courseData, HttpStatus.OK);
	}
	
	
	
	@GetMapping(path = "getmul/{location}")
	public ResponseEntity<List<Course>> fetloc(@PathVariable("location") String loc) throws NoContentException
	{
		List<Course> courseData = null;
		try
		{
			courseData = courseService.fetmul(loc);
		}catch(NoContentException a) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(courseData, HttpStatus.OK);
	}
	
	



	@PutMapping("/updatecourse/{id}")
	public ResponseEntity<Course> upthecourse(@PathVariable("id") Long id, @RequestBody Course course) {

		Optional<CourseEntity> courseData = courseRepository.findById(id);

		if (courseData.isPresent()) {

			return new ResponseEntity<>(courseService.updateRecordIntocourseTable(course), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletecourse/{id}")
	public ResponseEntity<HttpStatus> delthecourse(@PathVariable("id") Long id) {
		try {

			courseRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
