package com.angular.spring.dao;


import java.text.ParseException;
import java.util.List;

import com.angular.spring.entity.ParentTask;
//import com.projmgr.spring.model.Project;
//import com.projmgr.spring.model.Task;
//import com.projmgr.spring.model.TaskManager;
//import com.projmgr.spring.model.User;
import com.angular.spring.entity.Project;
import com.angular.spring.entity.Task;
import com.angular.spring.entity.TaskManager;
import com.angular.spring.entity.User;

public interface TaskManagerDAO {

	long save(TaskManager tb);
	
	TaskManager get(int id);
	
	List<TaskManager> list();
	
	void update (int id, TaskManager tasks);
	
	void delete (int id);

	List<TaskManager> view(String query) ;
	
	
	public long addProject(Project project) ;
	
	public long addUser(User user);
	
	public int addTask(Task task);
	
	public int addParentTask(ParentTask task);

	public void updateUser(String id, Task task); 
	
	public List<User> getAllUsers();
	
	public void userDelete(int id);
	
	public void updateUserwithId(int id, User user);
	
	public List<User> getAllUsers(int id) ;
	
	public List<Project> getProjects() throws ParseException;
	
	public List<Project> getProjectsOrder(int id) throws ParseException;
	
	public void updateProjectwithId(int id, Project project);

	public List<Task> getTasks() throws ParseException; 
	
	public List<Task> getTasksOrder(int id);
	
	public List<Project> getAllProjects(); 
	
	public List<ParentTask> getAllParentProjects(); 
	
	public void deleteTask(int id) ;
	
	public int addTask(int id,Task task);
	
	public int getProjectIds(Task task) ;
	
    public void endTask(int id);
	
}