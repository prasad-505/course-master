package com.accolite.course.service1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.repositories.CourseRepository;
import com.accolite.course.service.CourseService;

class CourseServiceTest {

	@InjectMocks
	CourseService cs;
	
	@Mock
	CourseRepository cr;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
   
	@Test
	void testFetmul() throws NoContentException {
		
		CourseEntity ci = new CourseEntity();
		ci.setId(2L);
		ci.setFeedback("Good");
		ci.setLastupdated("26/08/2020");
		
		//when(cs.fetmul( anyString() )).thenReturn(ci);
		when(cr.findById(anyInteger())).thenReturn(null);
		
		Course c2 = cs.fetchRecordFromcourseTable(1L);
		
		assertNotNull(c2);
		assertEquals("karnataka",c2.getLocation());
		
		
		
	}

	private Long anyInteger() {
		// TODO Auto-generated method stub
		return null;
	}
}
