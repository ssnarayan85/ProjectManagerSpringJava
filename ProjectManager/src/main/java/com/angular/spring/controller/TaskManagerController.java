package com.angular.spring.controller;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angular.spring.entity.ErrorResponse;
import com.angular.spring.entity.ParentTask;
import com.angular.spring.entity.Project;
import com.angular.spring.entity.Task;
import com.angular.spring.entity.TaskManager;
import com.angular.spring.entity.User;
import com.angular.spring.exception.TaskException;
import com.angular.spring.service.TaskManagerService;


@CrossOrigin("*")
@RestController
public class TaskManagerController  {

	@Autowired
	private TaskManagerService  taskManagerService;

//	@RequestMapping("/message")
//	public String message()
//	{
//		return "HI This is spring boot + angular + JPA";
//	}
	
	
	@GetMapping("/api/taskmanager")
	public ResponseEntity<List<TaskManager>> list() throws ParseException, TaskException{
		List<TaskManager> list =  taskManagerService.list();
			//return ResponseEntity.ok().body(list);
//		if(list.size()<1)
//				throw new TaskException("No Tasks are present now");
				return ResponseEntity.ok().body(list);
			
	}


	@GetMapping("/api/taskmanager/{id}")
	public ResponseEntity<TaskManager> get(@PathVariable("id") int id) throws TaskException{
		System.out.println("id="  + id);
		TaskManager tb =  taskManagerService.get(id);
		if(tb!= null)
		return ResponseEntity.ok().body(tb);
		else
			throw new TaskException("Given Task is not Present ");
	}
	
	
	@PostMapping("/api/task")
	
	public ResponseEntity<?> save(@RequestBody TaskManager tb){
		int id = (int) taskManagerService.save(tb);
		return ResponseEntity.ok().body("Task Id" + id);
	}


@PutMapping("/api/task/{id}")
	
	public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody TaskManager tb){
		  taskManagerService.update(id, tb);
		return ResponseEntity.ok().body("Updated Task Id") ;
	}
	

@DeleteMapping("/api/task/{id}")
	
	public ResponseEntity<?> delete(@PathVariable("id") int id){
	System.out.println("ID===" + id);
		  taskManagerService.delete(id);
		return ResponseEntity.ok().body("Deleted Task Id") ;
	}
	

@ExceptionHandler(TaskException.class)
public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	ErrorResponse error = new ErrorResponse();
	error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	error.setMessage(ex.getMessage());
	return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

	
@PostMapping("/api/addProject")

public ResponseEntity<?> addProject(@RequestBody Project project){
	int id = (int) taskManagerService.addProject(project);
	return ResponseEntity.ok().body("Task Id" + id);
}

@PostMapping("/api/addUser")

public ResponseEntity<?> addUser(@RequestBody User user){
	int id = (int) taskManagerService.addUser(user);
	return ResponseEntity.ok().body("User Id" + id);
}


@PostMapping("/api/addTask")

public ResponseEntity<?> addTask(@RequestBody Task task){
	int id = (int) taskManagerService.addTask(task);
	return ResponseEntity.ok().body("Task Id" + id);
}


@PutMapping("/api/addTask/{id}")

public ResponseEntity<?> updateTasks(@PathVariable("id") int id,@RequestBody Task task){
	  taskManagerService.updateTasks(id, task);
	return ResponseEntity.ok().body("Updated Task Id") ;
}


@PutMapping("/api/addProject/{id}")

public ResponseEntity<?> updateProject(@PathVariable("id") int id,@RequestBody Project project){
	System.out.println("1. entered update project");
	  taskManagerService.updateProject(id, project);
	return ResponseEntity.ok().body("Updated Task Id") ;
}


@GetMapping("/api/getAllUsers")
public ResponseEntity<List<User>> getAllUsers() throws ParseException, TaskException{
	List<User> list =  taskManagerService.getAllUsers();
			return ResponseEntity.ok().body(list);
		
}

@DeleteMapping("/api/user/{id}")
	
	public ResponseEntity<?> userDelete(@PathVariable("id") int id){
	System.out.println("ID===" + id);
		  taskManagerService.userDelete(id);
		return ResponseEntity.ok().body("Deleted Task Id") ;
	}


@PutMapping("/api/user/{id}")

public ResponseEntity<?> updateUser(@PathVariable("id") int id,@RequestBody User user){
	  taskManagerService.updateUser(id, user);
	return ResponseEntity.ok().body("Updated Task Id") ;
}


@GetMapping("/api/getAllUsers/{id}")
public ResponseEntity<List<User>> getAllUsersOrder(@PathVariable("id") int id) throws ParseException, TaskException{
	System.out.println("Order by "+ id);
	List<User> list =  taskManagerService.getAllUsers(id);
			return ResponseEntity.ok().body(list);
		
}

/* to be tested */

@GetMapping("/api/getProjects")
public ResponseEntity<List<Project>> getProjects() throws ParseException, TaskException{
	List<Project> list =  taskManagerService.getProjects();
			return ResponseEntity.ok().body(list);
}



@GetMapping("/api/getProjects/{id}")
public ResponseEntity<List<Project>> getProjectsOrder(@PathVariable("id") int id) throws ParseException, TaskException{
	System.out.println("Order by "+ id);
	List<Project> list =  taskManagerService.getProjectsOrder(id);
			return ResponseEntity.ok().body(list);
		
}



@GetMapping("/api/getTasks")
public ResponseEntity<List<Task>> getTasks() throws ParseException, TaskException{
	List<Task> list =  taskManagerService.getTasks();
			return ResponseEntity.ok().body(list);
}


@GetMapping("/api/getTasks/{id}")
public ResponseEntity<List<Task>> getTaskOrder(@PathVariable("id") int id) throws ParseException, TaskException{
	System.out.println("Order by "+ id);
	List<Task> list =  taskManagerService.getTasksOrder(id);
			return ResponseEntity.ok().body(list);
		
}


@GetMapping("/api/getAllProjects")
public ResponseEntity<List<Project>> getAllProjects() throws ParseException, TaskException{
	List<Project> list =  taskManagerService.getAllProjects();
			return ResponseEntity.ok().body(list);
}



@GetMapping("/api/getAllParentProjects")
public ResponseEntity<List<ParentTask>> getAllParentProjects() throws ParseException, TaskException{
	List<ParentTask> list =  taskManagerService.getAllParentProjects();
			return ResponseEntity.ok().body(list);
}

/*to be deleted*/
@PostMapping("/api/addParentTask")
public ResponseEntity<?> addParentTask(@RequestBody ParentTask pt) throws ParseException, TaskException{
	int ret =  taskManagerService.addParentTask(pt);
			return ResponseEntity.ok().body("Parent Task added");
}


@DeleteMapping("/api/deleteTask/{id}")

public ResponseEntity<?> deleteTask(@PathVariable("id") int id){
System.out.println("ID===" + id);
	  taskManagerService.deleteTask(id);
	return ResponseEntity.ok().body("Deleted Task Id") ;
}


@PutMapping("/api/endTask/{id}")

public ResponseEntity<?> endTask(@PathVariable("id") int id){
	  taskManagerService.endTask(id);
	return ResponseEntity.ok().body("Updated Task Id") ;
}


}