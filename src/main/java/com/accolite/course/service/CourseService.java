package com.accolite.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.entities.Creator;
import com.accolite.course.entities.Skill;
import com.accolite.course.entities.StudentEntity;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.models.Student;
import com.accolite.course.repositories.CourseRepository;
import com.accolite.course.repositories.StudentRepository;


@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JavaMailSender JM;
	
	
	public void sendmail(String to,String body,String sub)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("sorganviprasad@gmail.com");
		msg.setTo(to);
		msg.setText(body);
		msg.setSubject(sub);
		JM.send(msg);
		System.out.println("mail send......");
	}
	
	
	
	public void sendmail2(String body,String sub) throws NoContentException
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("sorganviprasad@gmail.com");
		List<String> entity = studentRepository.getAll();
		if(entity.isEmpty())
	      {
	    	  throw new NoContentException(HttpStatus.NO_CONTENT);
	      }
		int i= entity.size()-1;
		while(i>=0)
		{
		msg.setTo(entity.get(i));
		msg.setText(body);
		msg.setSubject(sub);
		JM.send(msg);
		i--;
		System.out.println("mail send......");
		}
	}
	
	public List<Student> getStudents() throws NoContentException
	{
		List<StudentEntity> entity = studentRepository.getAll1();
		if(entity.isEmpty())
	      {
	    	  throw new NoContentException(HttpStatus.NO_CONTENT);
	      }
		int i=entity.size()-1;
		 ArrayList<Student> i1=new ArrayList<Student>(i);
	      while(i>=0)
	      {
	    	  i1.add(mapEntityToObject(entity.get(i)));
	    	  i--;
	      }
	      return i1;
	}
	
	public List<Student> stubyemail(String email) throws NoContentException
	{
	      List<StudentEntity> entity= studentRepository.findByEmail(email);
	      if(entity.isEmpty())
	      {
	    	  throw new NoContentException(HttpStatus.NO_CONTENT);
	      }
	      int i=entity.size()-1;
	      ArrayList<Student> i1=new ArrayList<Student>(i);
	      while(i>=0)
	      {
	    	  i1.add(mapEntityToObject(entity.get(i)));
	    	  i--;
	      }
	      return i1;
	}
	
	
	
	public Course saveIntocourseItemTable(Course course) throws NoContentException {
		CourseEntity entity = courseRepository.save(mapObjectToEntity1(course));
		sendmail2("new course added","go and check");
		return mapEntityToObject1(entity);
	}
	
	public Student saveIntocourseItemTable(Student course) {
		StudentEntity entity = studentRepository.save(mapObjectToEntity(course));
		return mapEntityToObject(entity);
	}

	public CourseEntity mapObjectToEntity1(Course course) {
		CourseEntity entity = new CourseEntity();
		
		
		entity.setId(course.getId());
		entity.setDescription(course.getDescription());
		entity.setPrerequesite(course.getPrerequesite());
		
		entity.setLastupdated(course.getLastupdated());
		
		entity.setFeedback(course.getFeedback());
		entity.setSkill(addSkill(course));
		entity.setCreator(addCreator(course));
		entity.setLocation(course.getLocation());
		
		return entity;
	}
	
	public StudentEntity mapObjectToEntity(Student course) {
		StudentEntity entity = new StudentEntity();
		
		entity.setEmail(course.getEmail());
		entity.setId(course.getId());
		entity.setName(course.getName());
		return entity;
	}

	private List<Skill> addSkill(Course course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkills().size();
		long skillId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			skillId = course.getSkills().get(i).getId();
			name = course.getSkills().get(i).getName();

			Skill skill = new Skill(skillId, name);
			//skillRepository.save(skill);
			skillList.add(skill);

		}

		return skillList;

	}

	private List<Skill> addSkill(CourseEntity course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkill().size();
		long skillId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			skillId = course.getSkill().get(i).getId();
			name = course.getSkill().get(i).getName();

			Skill skill = new Skill(skillId, name);
			
			skillList.add(skill);

		}

		return skillList;

	}

	private List<Creator> addCreator(Course course) {

		
		List<Creator> creatorList = new ArrayList<>();
		int n = course.getCreator().size();
		long creatorId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			creatorId = course.getCreator().get(i).getId();
			name = course.getCreator().get(i).getName();

			Creator creator = new Creator(creatorId, name);
			
			creatorList.add(creator);

		}

		return creatorList;

	}

	private List<Creator> addCreator(CourseEntity course) {		
		List<Creator> creatorList = new ArrayList<>();
		int n = course.getCreator().size();
		long creatorId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			creatorId = course.getCreator().get(i).getId();
			name = course.getCreator().get(i).getName();

			Creator creator = new Creator(creatorId, name);
			
			creatorList.add(creator);

		}

		return creatorList;

	}

	public Course mapEntityToObject1(CourseEntity entity) {

		Course course = new Course();
		course.setId(entity.getId());
		course.setDescription(entity.getDescription());
		course.setPrerequesite(entity.getPrerequesite());
		course.setLastupdated(entity.getLastupdated());
		course.setFeedback(entity.getFeedback());
		course.setSkills(addSkill(entity));
		course.setCreator(addCreator(entity));
		course.setLocation(entity.getLocation());

		return course;
	}
	
	public Student mapEntityToObject(StudentEntity course) {
		Student entity = new Student();
		
		entity.setEmail(course.getEmail());
		entity.setId(course.getId());
		entity.setName(course.getName());
		return entity;
	}


	public Course fetchRecordFromcourseTable(Long id) throws NoContentException {

		Optional<CourseEntity> entity = courseRepository.findById(id);
		if (!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject1(entity.get());

	}

	public Course updateRecordIntocourseTable(Course course) {
		CourseEntity entity = courseRepository.save(mapObjectToEntity1(course));
		return mapEntityToObject1(entity);

	}
	
	
	public int add(int a, int b)
	{
		int sum = a+b;
		return sum;
	
	}

	
	public List<Course> fetmul(String location) throws NoContentException
	{
	      List<CourseEntity> entity= courseRepository.findByLocation(location);
	      if(entity.isEmpty())
	      {
	    	  throw new NoContentException(HttpStatus.NO_CONTENT);
	      }
	      int i=entity.size()-1;
	      ArrayList<Course> i1=new ArrayList<Course>(i);
	      while(i>=0)
	      {
	    	  i1.add(mapEntityToObject1(entity.get(i)));
	    	  i--;
	      }
	      return i1;
	}
}