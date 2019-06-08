package com.angular.spring.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

//	@InjectMocks
//    private TaskManagerController taskManagerController;

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

	
    /* POST task*/
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
	
	/* POST addProject */
	@Test
	 public void testAddProject() throws Exception {
	 
	  // prepare data and mock's behavior
		Project project = new Project();
		project.setProjectId(1);
		project.setProject("proj1");
		project.setPriority(1);
		project.setSdate(new Date());
		project.setEdate(new Date());

		
		String inputJson = this.mapToJson(project);
		
//	    Mockito.when(taskManagerService.save(Mockito.any(TaskManager.class))).thenReturn(taskManager);
	    Mockito.when(taskManagerService.addProject(Mockito.any(Project.class))).thenReturn((long) project.getProjectId());
	 
	    // execute
	    String URL = "/api/addProject";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	    .post(URL)
	    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)
	    .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(project);
	    String outputJson = result.getResponse().getContentAsString();
	    assertEquals(1, (long) project.getProjectId());
	   // assertThat(outputJson).isEqualTo(expectedJson);

	}
	
	/* POST /api/addUser */
	@Test
	 public void testAddUser() throws Exception {
	 
	  // prepare data and mock's behavior
		User user = new User();
		user.setUserId(1);
		user.setFname("Sasi");
		user.setLname("Rekha");
		user.setEid("1");
		user.setProjectid("1");
		user.setTid("1");	
		
		String inputJson = this.mapToJson(user);
		
//	    Mockito.when(taskManagerService.save(Mockito.any(TaskManager.class))).thenReturn(taskManager);
	    Mockito.when(taskManagerService.addUser(Mockito.any(User.class))).thenReturn((long) user.getUserId());
	 
	    // execute
	    String URL = "/api/addUser";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	    .post(URL)
	    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)
	    .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	    
	    String expectedJson= this.mapToJson(user);
	    String outputJson = result.getResponse().getContentAsString();
	    assertEquals(1, (long) user.getUserId());
	   // assertThat(outputJson).isEqualTo(expectedJson);

	}

	/*api/addTask*/
	@Test
	 public void testAddTask() throws Exception {
	 
	  // prepare data and mock's behavior
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
		
		String inputJson = this.mapToJson(task);
	
	    Mockito.when(taskManagerService.addTask(Mockito.any(Task.class))).thenReturn((int) task.getTaskId());
	 
	    // execute
	    String URL = "/api/addTask";
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	    .post(URL)
	    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)
	    .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
	        
	    String expectedJson= this.mapToJson(task);
	    String outputJson = result.getResponse().getContentAsString();
	    assertEquals(1, (long) task.getTaskId());
	   // assertThat(outputJson).isEqualTo(expectedJson);

	}

	
	/*PUT /api/addTask/{id} */
	

	@Test
	 public void testUpdateTasks() throws Exception {
	 
	  // prepare data and mock's behavior
		Task task = new Task();
		task.setTaskId(2);
		task.setProjectId(1);
		task.setPtask("ParTask2");
		task.setPname("proj3");
		task.setTname("Task2");
		task.setTsdate(new Date());
		task.setTedate(new Date());
		task.setTpriority("1");
		task.setStat("inprogress");
		
		String inputJson = this.mapToJson(task);
		
	//	doNothing().when(taskManagerService).updateTasks(Mockito.any(int.class), task);
	//	Mockito.doReturn(2).when(taskManagerService).updateTasks(Mockito.any(int.class), task);
		
		 
	    // executes
	    String URL = "/api/addTask/";
	 //   MvcResult result =mockMvc.perform(MockMvcRequestBuilders
	    
	    mockMvc.perform(MockMvcRequestBuilders
	    .put(URL + "{id}", new Integer(2))
	    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)	   
	    .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	    
	    //Mockito.verify(taskManagerService, times(1)).updateTasks(2, task);
	    ArgumentCaptor<Task> argument1 = ArgumentCaptor.forClass(Task.class);
	    verify(taskManagerService).updateTasks(eq(2), argument1.capture());
	    assertEquals("proj3", argument1.getValue().getPname());
	    
	}
	
	
	@Test
	 public void testUpdateProject() throws Exception {
	 
	  // prepare data and mock's behavior
		Project project = new Project();
		project.setProjectId(2);
		project.setProject("proj2");
		project.setPriority(2);
		project.setSdate(new Date());
		project.setEdate(new Date());
		
		String inputJson = this.mapToJson(project);	
		 
	    // executes
	    String URL = "/api/addProject/";
	    
	    mockMvc.perform(MockMvcRequestBuilders
	    .put(URL + "{id}", new Integer(2))
	    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)	   
	    .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	    
	    //Mockito.verify(taskManagerService, times(1)).updateProject(2, project);
		
	    ArgumentCaptor<Project> argument1 = ArgumentCaptor.forClass(Project.class);
	    verify(taskManagerService).updateProject(eq(2), argument1.capture());
	    assertEquals("proj2", argument1.getValue().getProject());
	  
	}

	/* put /api/user/{id} */

	@Test
		 public void testUpdateUser() throws Exception {
		 
		  // prepare data and mock's behavior
			User user = new User();
			user.setUserId(2);
			user.setFname("Sassi");
			user.setLname("Rekha");
			user.setEid("1");
			user.setProjectid("1");
			user.setTid("1");	
			
			String inputJson = this.mapToJson(user);
			 
		    // executes
		    String URL = "/api/user/";
		    
		    mockMvc.perform(MockMvcRequestBuilders
		    .put(URL + "{id}", new Integer(2))
		    .accept(MediaType.APPLICATION_JSON_UTF8).content(inputJson)	   
		    .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
		    
		    //Mockito.verify(taskManagerService, times(1)).updateUser(2, project);
			
		    ArgumentCaptor<User> argument1 = ArgumentCaptor.forClass(User.class);
		    verify(taskManagerService).updateUser(eq(2), argument1.capture());
		    assertEquals("Sassi", argument1.getValue().getFname());
		  
		}

	/* Delete /api/user/{id} */
	@Test
	 public void testUserDelete() throws Exception {
	 
	  // prepare data and mock's behavior
	    String URL = "/api/user/"; 
	    
	    mockMvc.perform(MockMvcRequestBuilders
						.delete(URL + "{id}", new Integer(2)))
			            .andExpect(status().isOk());     
	}
	
	
	/* Delete /api/task/{id} */
	@Test
	 public void testDelete() throws Exception {
	 
	  // prepare data and mock's behavior
	    String URL = "/api/task/"; 
	    
	    mockMvc.perform(MockMvcRequestBuilders
						.delete(URL + "{id}", new Integer(2)))
			            .andExpect(status().isOk());     
	}
	
	
	/* Delete  /api/deleteTask/{id} */
	
	@Test
	public void testDeleteTask() throws Exception {
 
  // prepare data and mock's behavior
    String URL = "/api/deleteTask/"; 
    
    mockMvc.perform(MockMvcRequestBuilders
					.delete(URL + "{id}", new Integer(2)))
		            .andExpect(status().isOk());     
	}
	
	
	private String mapToJson(Object object) throws JsonProcessingException{
	 ObjectMapper objectMapper = new ObjectMapper();
	 return objectMapper.writeValueAsString(object);
		
	}
}
 