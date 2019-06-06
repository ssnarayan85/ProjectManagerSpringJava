package com.angular.spring.controller;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

//import com.angular.spring.config.AppConfig;
import com.angular.spring.entity.Project;
import com.angular.spring.entity.Task;
import com.angular.spring.entity.TaskManager;
import com.angular.spring.entity.User;
import com.angular.spring.service.TaskManagerService;


class TaskManagerController1{
	
}

//@Configuration
//@ComponentScan(basePackages = {"com.angular.spring"})

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=AppConfig.class)

@RunWith(SpringRunner.class)
@SpringBootTest

public class TaskManagerMvcControllerTest {

	@Autowired
	private TaskManagerService  taskManagerServiceTest;
	
	
	@BeforeClass
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	@Test
	public void testListSize() {
		List<TaskManager> list=taskManagerServiceTest.list();
		System.out.println("list size =" + list.size());
		assertEquals(74, list.size());
	}
	
	@Test
	public void testData() {
		TaskManager tb =  taskManagerServiceTest.get(1);
		assertEquals("Task1", tb.getTask());
	}
	
	@Test
	public void testTaskData() {
		TaskManager tb =  taskManagerServiceTest.get(1);
		//assertNull(tb);
		assertNotNull(tb);
	}
	
	@Test
	public void testData1() {
		TaskManager tb =  taskManagerServiceTest.get(1);
		
		System.out.println("getParentId =" + tb.getParentId() + "tb=" + tb);
		assertEquals("ParTask1", tb.getParentId());
		System.out.println("getParentId =" + tb.getParentId());
	}
	
	@Test
	public void testgetusers() {
		List<User> list =  taskManagerServiceTest.getAllUsers();
		assertNotNull(list);
	}
	
	@Test
	public void testuserid() {
		List<User> list=taskManagerServiceTest.getAllUsers(1);
		assertNotNull(list);
	}
	
	@Test
	public void testprojects() throws ParseException {
		List<Project> list =  taskManagerServiceTest.getProjects();
		assertNotNull(list);
	}

	
	@Test
	public void testprojectsId() throws ParseException {
		List<Project> list =  taskManagerServiceTest.getProjectsOrder(1);
		assertNotNull(list);
	}
	
	@Test
	public void testtasks() throws ParseException {
		List<Task> list =  taskManagerServiceTest.getTasks();
		assertNotNull(list);
	}
	
	@Test
	public void testtasksId() throws ParseException {
		List<Task> list =  taskManagerServiceTest.getTasksOrder(1);
		assertNotNull(list);
	}
	
	
}
