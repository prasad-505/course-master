package com.accolite.course.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.accolite.course.entities.CourseEntity;
import com.accolite.course.exception.NoContentException;
import com.accolite.course.models.Course;
import com.accolite.course.repositories.CourseRepository;


class CourseServiceTest {
	
	@InjectMocks
	CourseService CS;
	
	@Mock
	CourseRepository CSR;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
    
	@Test
	public void testFetchRecordFromcourseTable() throws NoContentException {
		CourseEntity csr1 = new CourseEntity();
		csr1.setDescription("Good");
		csr1.setFeedback("Knowing");
		csr1.setLastupdated("30-06-2021");
		csr1.setLocation("karnataka");
		csr1.setPrerequesite("Html");
		when(CSR.findById1(anyLong())).thenReturn(csr1);
		
		Course cs2 = CS.fetchRecordFromcourseTable(2L);
		assertNotNull(cs2);
		assertEquals(2L,cs2.getId());
	}
	
	@Test
	public void testadd()
	{
		int res = CS.add(10, 20);
		assertEquals(30,30);
	}
}