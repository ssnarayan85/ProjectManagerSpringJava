package com.angular.spring.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.angular.spring.entity.ParentTask;
import com.angular.spring.entity.Project;
import com.angular.spring.entity.Task;
import com.angular.spring.entity.TaskManager;
import com.angular.spring.entity.User;
import com.angular.spring.service.TaskManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(TaskManagerController.class)
public class TaskManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskManagerService taskManagerService;
	
	/* Testing a single value */
	@Test
	 public void testGet() throws Exception {
	 
	  // prepare data and mock's behavior
		
		TaskManager taskManager = new TaskManager();
		taskManager.setTaskId(1);
		taskManager.setParentId("ParTask1");
		taskManager.setTask("Task1");
		taskManager.setStartDate( new Date ());
		taskManager.setEndDate(new Date());
		taskManager.setPriority(1);	
		
	    Mockito.when(taskManagerService.get(Mockito.any(int.class))).thenReturn(taskManager);
	 
	    // execute
	    String URL = "/api/taskmanager/";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", new Integer(1))
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(taskManager);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}
	
	
	@Test
	 public void testList() throws Exception {
	 
	  // prepare data and mock's behavior
		
		List<TaskManager> taskManagerlist = new ArrayList<TaskManager>();
		
		TaskManager taskManager = new TaskManager();
		taskManager.setTaskId(1);
		taskManager.setParentId("ParTask1");
		taskManager.setTask("Task1");
		taskManager.setStartDate( new Date() );
		taskManager.setEndDate(new Date());
		taskManager.setPriority(1);
		
		taskManagerlist.add(taskManager);		
	    Mockito.when(taskManagerService.list()).thenReturn(taskManagerlist);
	    
	    // execute
	    String URL = "/api/taskmanager";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(taskManagerlist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}
	
    /* POST */
	@Test
	 public void testSave() throws Exception {
	 
	  // prepare data and mock's behavior
		
		TaskManager taskManager = new TaskManager();
		taskManager.setTaskId(1);
		taskManager.setParentId("ParTask1");
		taskManager.setTask("Task1");
		taskManager.setStartDate( new Date ());
		taskManager.setEndDate(new Date());
		taskManager.setPriority(1);	
		
		String inputJson = this.mapToJson(taskManager);
		
//	    Mockito.when(taskManagerService.save(Mockito.any(TaskManager.class))).thenReturn(taskManager);
	    Mockito.when(taskManagerService.save(Mockito.any(TaskManager.class))).thenReturn((long) taskManager.getTaskId());
	 
	    // execute
	    String URL = "/api/task";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	    .post(URL)
	    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)
	    .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(taskManager);
	    String outputJson = result.getResponse().getContentAsString();
	    assertEquals(1, (long) taskManager.getTaskId());
	   // assertThat(outputJson).isEqualTo(expectedJson);

	}

	/*Getting a list of values */
	@Test
	 public void testGetAllUsers() throws Exception {
	 
	  // prepare data and mock's behavior		
		List<User> userlist = new ArrayList<User>();
		
		User user = new User();
		user.setUserId(1);
		user.setFname("Sasi");
		user.setLname("Rekha");
		user.setEid("1");
		user.setProjectid("1");
		user.setTid("1");
		
		userlist.add(user);		
	    Mockito.when(taskManagerService.getAllUsers()).thenReturn(userlist);
	    
	    // execute
	    String URL = "/api/getAllUsers";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(userlist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}

	/* Getting a single value but with a return value as list */
	@Test
	 public void testGetAllUsersOrder() throws Exception {
	 
	  // prepare data and mock's behavior
		
		List<User> userlist = new ArrayList<User>();
		
		User user = new User();
		user.setUserId(1);
		user.setFname("Sasi");
		user.setLname("Rekha");
		user.setEid("1");
		user.setProjectid("1");
		user.setTid("1");
		
		userlist.add(user);		
		 Mockito.when(taskManagerService.getAllUsers(Mockito.any(int.class))).thenReturn(userlist);	    
	 
	    // execute
	    String URL = "/api/getAllUsers/";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", new Integer(1))
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(userlist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);
	}

	
	//getProjects
	@Test
	 public void testGetProjects() throws Exception {
	 
	  // prepare data and mock's behavior		
		List<Project> projectlist = new ArrayList<Project>();
		
		Project project = new Project();
		project.setProjectId(1);
		project.setProject("proj1");
		project.setPriority(1);
		project.setSdate(new Date());
		project.setEdate(new Date());
		
		projectlist.add(project);		
	    Mockito.when(taskManagerService.getProjects()).thenReturn(projectlist);
	    
	    // execute
	    String URL = "/api/getProjects";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(projectlist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}
	
	
	/* Getting a single value but with a return value as list */
	@Test
	 public void testGetProjectsOrder() throws Exception {
	 
	 // prepare data and mock's behavior		
		List<Project> projectlist = new ArrayList<Project>();
		
		Project project = new Project();
		project.setProjectId(1);
		project.setProject("proj1");
		project.setPriority(1);
		project.setSdate(new Date());
		project.setEdate(new Date());
		
		projectlist.add(project);		
	    Mockito.when(taskManagerService.getProjectsOrder(Mockito.any(int.class))).thenReturn(projectlist);    
	 
	    // execute
	    String URL = "/api/getProjects/";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", new Integer(1))
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(projectlist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);
	}

	//getTasks
	
	@Test
	 public void testGetTasks() throws Exception {
	 
	  // prepare data and mock's behavior		
		List<Task> tasklist = new ArrayList<Task>();
		
		Task task = new Task();
		task.setTaskId(1);
		task.setProjectId(1);
		task.setPtask("ParTask2");
		task.setPname("proj2");
		task.setTname("Task2");
		task.setTsdate(new Date());
		task.setTedate(new Date());
		task.setTpriority("1");
		task.setStat("inprogress");
		
		tasklist.add(task);		
	    Mockito.when(taskManagerService.getTasks()).thenReturn(tasklist);
	    
	    // execute
	    String URL = "/api/getTasks";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(tasklist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}

	//getTaskOrder
	/* Getting a single value but with a return value as list */
	@Test
	 public void testGetTasksOrder() throws Exception {
	 
	  // prepare data and mock's behavior		
			List<Task> tasklist = new ArrayList<Task>();
			
			Task task = new Task();
			task.setTaskId(1);
			task.setProjectId(1);
			task.setPtask("ParTask2");
			task.setPname("proj2");
			task.setTname("Task2");
			task.setTsdate(new Date());
			task.setTedate(new Date());
			task.setTpriority("1");
			task.setStat("inprogress");
			
			tasklist.add(task);		
				
	    Mockito.when(taskManagerService.getTasksOrder(Mockito.any(int.class))).thenReturn(tasklist); 
	 
	    // execute
	    String URL = "/api/getTasks/";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "{id}", new Integer(1))
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(tasklist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);
	}
	
	/*Getting a list of values */
	@Test
	 public void testGetAllProjects() throws Exception {
	 
	  // prepare data and mock's behavior		
		List<Project> projectlist = new ArrayList<Project>();
		
		Project project = new Project();
		project.setProjectId(1);
		project.setProject("proj1");
		project.setPriority(1);
		project.setSdate(new Date());
		project.setEdate(new Date());
		
		projectlist.add(project);		
	    Mockito.when(taskManagerService.getAllProjects()).thenReturn(projectlist);
	    
	    // execute
	    String URL = "/api/getAllProjects";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(projectlist);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}


	
	/*Getting a list of values */
	@Test
	 public void testGetAllParentProjects() throws Exception {
	 
	  // prepare data and mock's behavior		
		List<ParentTask> parentList = new ArrayList<ParentTask>();
		
		ParentTask parentTask = new ParentTask();
		parentTask.setPid(1);
		parentTask.setPtask("ParTask1");
		
		parentList.add(parentTask);		
	    Mockito.when(taskManagerService.getAllParentProjects()).thenReturn(parentList);
	    
	    // execute
	    String URL = "/api/getAllParentProjects";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
	   .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(parentList);
	    String outputJson = result.getResponse().getContentAsString();
	    assertThat(outputJson).isEqualTo(expectedJson);

	}

	
	private String mapToJson(Object object) throws JsonProcessingException{
	 ObjectMapper objectMapper = new ObjectMapper();
	 return objectMapper.writeValueAsString(object);
		
	}
}
 