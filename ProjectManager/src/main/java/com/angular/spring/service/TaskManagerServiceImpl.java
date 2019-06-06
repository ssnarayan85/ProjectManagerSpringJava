package com.angular.spring.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import javax.transaction.Transactional; original
import org.springframework.transaction.annotation.Transactional; //new

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.angular.spring.dao.TaskManagerDAO;
import com.angular.spring.entity.ParentTask;
import com.angular.spring.entity.Project;
import com.angular.spring.entity.Task;
import com.angular.spring.entity.TaskManager;
import com.angular.spring.entity.User;

//import com.projmgr.spring.model.Task;
//import com.projmgr.spring.model.TaskManager;
//import com.projmgr.spring.model.User;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	private TaskManagerDAO taskManagerDAO;
	
	
	@Override
	@Transactional
	public long save(TaskManager tb) {
		// TODO Auto-generated method stub
		return taskManagerDAO.save(tb);
	}


	@Override
	@Transactional
	public TaskManager get(int id) {
		return taskManagerDAO.get(id);	
	}

	@Override
	@Transactional
	public List<TaskManager> list() {
		// TODO Auto-generated method stub
		return taskManagerDAO.list();
	}
	

	@Override
	@Transactional
	public void update(int id, TaskManager tasks) {
		taskManagerDAO.update(id, tasks);
		
	}

	
	@Override
	@Transactional
	public void delete(int id) {
		
		taskManagerDAO.delete(id);
	}

	@Override
	@Transactional
	public List<TaskManager> view(String Task,String parentId,String PriorityFrom,String Priorityto,String startDate,String endDate) {
		String query="from TaskManager where ";
		String combineQuery ="";
		if(Task!=null && Task!="")
			combineQuery = combineQuery +" Task ='" + Task +"'";
		if(parentId!=null && parentId !="") {
		if(combineQuery!="")	{
					combineQuery=combineQuery +" and parentId = '"+ parentId+"'";
		}
		else 
			combineQuery=combineQuery+" parentId ='"+ parentId+"'";
		}
		if(PriorityFrom!=null && PriorityFrom!="" && Priorityto!=null && Priorityto!="") {
			if(combineQuery!="") {	
			combineQuery=combineQuery+" and priority >= "+ PriorityFrom + " and "+ "priority <= "+ Priorityto ;
			}
			else
			combineQuery=combineQuery+" priority >= "+ PriorityFrom + " and "+ "priority <= "+Priorityto ;
		}else if(PriorityFrom!=null && PriorityFrom!="" && (Priorityto==null || Priorityto=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and priority >= "+ PriorityFrom ;
			}
			else
			{
				combineQuery=combineQuery+" priority >= "+ PriorityFrom ;
			}
		}
		else if(Priorityto!=null && Priorityto!="" && (PriorityFrom==null || PriorityFrom=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and priority <= "+ Priorityto ;
			}
			else
			{
				combineQuery=combineQuery+" priority <= "+ Priorityto ;
			}
		}
		if(startDate!=null && startDate!="" && endDate!=null && endDate!="") {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and startDate>= '"+ startDate + "' and "+ " endDate <= '"+ endDate +"'" ;
				}
				else
				combineQuery=combineQuery+" startDate >= '"+ startDate + "' and "+ " endDate <= '"+endDate  +"'" ;
		}else if(startDate!=null && startDate!="" && (endDate==null || endDate=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and startDate >= '"+ startDate  +"'" ;
			}
			else
			{
				combineQuery=combineQuery+" startDate >= '"+ startDate  +"'" ;
			}
		}else if(endDate!=null && endDate!="" && (startDate==null || startDate=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and endDate <= '"+ endDate +"'" ;
			}
			else
			{
				combineQuery=combineQuery+" endDate <= '"+ endDate +"'" ;
			}
		}

		query=query+combineQuery;
	
		/*leave the below comment */
		/*if(Task!=null && Task!="" && parentId!=null && parentId !="" && PriorityFrom!=null && PriorityFrom!="" && Priorityto!=null && Priorityto!="" 
			&& startDate!=null && startDate!="" && endDate!=null && endDate!=""	) {
			query="from TaskManager ";
		}****/


		if(Task!="" && parentId !="" && PriorityFrom!="" && Priorityto!="" 
		&& startDate!="" && endDate!=""	) {
		query=query;
		}
		else {
			query="from TaskManager ";	
		}
		
		List<TaskManager> li =taskManagerDAO.view(query);
		return li;
	}
	
	/*  Second Phase */
	
	
	@Override
	@Transactional
	public long addProject(Project project) {
		// TODO Auto-generated method stub
		return taskManagerDAO.addProject(project);
	}


	@Override
	@Transactional
	public long addUser(User user) {
		// TODO Auto-generated method stub
		return taskManagerDAO.addUser(user);
	}


	@Override
	@Transactional
	public int addTask(Task task) {

		if("true".equalsIgnoreCase(task.getPcheck())) {
		ParentTask pt= new ParentTask();
		pt.setPtask(task.getTname());
		return taskManagerDAO.addParentTask(pt);
		}else {
			 int taskId= taskManagerDAO.addTask(task);
			 task.setTaskId(taskId);
			 int projectId=taskManagerDAO.getProjectIds(task);
			 task.setProjectId(projectId);
			 taskManagerDAO.updateUser(task.getEmpId(),task);
			 return taskId;
		}
		
	}
	
	/* to be deleted */
	public int addParentTask(ParentTask pt) {
		taskManagerDAO.addParentTask(pt);
		System.out.println("Parent Task added");
		return 1;
	}
	
	
	@Override
	@Transactional
	public void updateTasks(int id, Task task) {
		
			 taskManagerDAO.updateUser(task.getEmpId(),task);
			  taskManagerDAO.addTask(id,task);
		}
		
	
	@Override
	@Transactional
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return taskManagerDAO.getAllUsers();
	}


	@Override
	@Transactional
	public void userDelete(int id) {
		taskManagerDAO.userDelete(id);
	}
	
	@Override
	@Transactional
	public void updateUser(int id, User user) {
		taskManagerDAO.updateUserwithId(id, user);
		
	}

	@Override
	@Transactional
	public List<User> getAllUsers(int id) {
		// TODO Auto-generated method stub
		return taskManagerDAO.getAllUsers(id);
	}
	
	@Override
	@Transactional
	public List<Project> getProjects() throws ParseException {
		return taskManagerDAO.getProjects();
	}

	
	
	@Override
	@Transactional
	public List<Project> getProjectsOrder(int id) throws ParseException {
		return taskManagerDAO.getProjectsOrder(id);
	}

	
	@Override
	@Transactional
	public void updateProject(int id, Project project) {
		taskManagerDAO.updateProjectwithId(id, project);
		
	}
	
	@Override
	@Transactional
	public void endTask(int id) {
		taskManagerDAO.endTask(id);
		
	}
	
	@Override
	@Transactional
	public List<Task> getTasks() throws ParseException {
		// TODO Auto-generated method stub
		return taskManagerDAO.getTasks();
	}


	@Override
	@Transactional
	public List<Task> getTasksOrder(int id) throws ParseException {
		// TODO Auto-generated method stub
		return taskManagerDAO.getTasksOrder(id);
	}

	
	@Override
	@Transactional
	public List<Project> getAllProjects() throws ParseException {
		return taskManagerDAO.getAllProjects();
	}
	
	
	@Override
	@Transactional
	public List<ParentTask> getAllParentProjects() throws ParseException {
		// TODO Auto-generated method stub
		return taskManagerDAO.getAllParentProjects();
	}
	
	
	@Override
	@Transactional
	public void deleteTask(int id) {
		taskManagerDAO.deleteTask(id);
	}
	
	
}