package com.angular.spring.dao;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angular.spring.entity.ParentTask;
import com.angular.spring.entity.Project;
import com.angular.spring.entity.Task;
import com.angular.spring.entity.TaskManager;
import com.angular.spring.entity.User;
import com.angular.spring.repository.ParentTaskRepository;
import com.angular.spring.repository.ProjectRepository;

//import com.projmgr.spring.model.Task;
//import com.projmgr.spring.model.TaskManager;

@Repository
public class TaskManagerDAOImpl implements TaskManagerDAO{

//	List<ParentTask> list =new ArrayList<>();
	public TaskManagerDAOImpl() {
		System.out.println("task Manager Dao layer is created");
	}

	@Autowired
	private EntityManager entityManager;
//	@Autowired
//	private ParentTaskRepository taskmanagerRepository;
//	
//	@Autowired
//	private ProjectRepository projectRepository;
	

//	@Autowired
//	private SessionFactory sessionFactory; 
	

 	public long save(TaskManager tb) {
//		sessionFactory.getCurrentSession().save(tb);
 		entityManager.unwrap(Session.class).save(tb);
		return tb.getTaskId();
	}


	@Override
	public TaskManager get(int id) {
//		return sessionFactory.getCurrentSession().get(TaskManager.class, id);
		return entityManager.unwrap(Session.class).get(TaskManager.class, id);		
	}

 	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskManager> list() {
//		List<TaskManager> l= sessionFactory.getCurrentSession().createQuery("from TaskManager").list();
		List<TaskManager> l= entityManager.unwrap(Session.class).createQuery("from TaskManager").list();
		return l;
	}

 	
	@Override
	public void update(int id, TaskManager tasks) {
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		TaskManager oldTask =session.byId(TaskManager.class).load(id);
		oldTask.setTask(tasks.getTask());
		oldTask.setParentId(tasks.getParentId());
		oldTask.setPriority(tasks.getPriority());
		oldTask.setStartDate(tasks.getStartDate());
		oldTask.setEndDate(tasks.getEndDate());
		
		session.flush();
	}

	
	@Override
	public void delete(int id) {
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		TaskManager oldTask =session.byId(TaskManager.class).load(id);
		session.delete(oldTask);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskManager> view(String query) {
		System.out.println("query=== "+query);
//		List<TaskManager> l= sessionFactory.getCurrentSession().createQuery(query).list();
		List<TaskManager> l= entityManager.unwrap(Session.class).createQuery(query).list();
		return l;
	}

	
	/* Second Phase*/

	@Override
	public long addProject(Project project) {
//    	sessionFactory.getCurrentSession().save(project);
//		projectRepository.save(project);
		
		Session currentSession =entityManager.unwrap(Session.class);
		currentSession.save(project);
		return project.getProjectId();
	}


	@Override
	public long addUser(User user) {
		Session currentSession =entityManager.unwrap(Session.class);
		currentSession.save(user);
		return user.getUserId();
	}

	
	@Override
	public int addParentTask(ParentTask pt) {
//		sessionFactory.getCurrentSession().save(pt);
//  	this.list.add(task);
//		taskmanagerRepository.save(pt);
		
		Session currentSession =entityManager.unwrap(Session.class);
		currentSession.save(pt);
		System.out.println("Parent added");
		return pt.getPid();

	}
	

	@Override
	public int addTask(Task task) {
//		sessionFactory.getCurrentSession().save(task);
		
		Session currentSession =entityManager.unwrap(Session.class);
		currentSession.save(task);
		return task.getTaskId();
	}

	

	@Override
	public int addTask(int id,Task task) {
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		Task oldTask =session.byId(Task.class).load(id);
		oldTask.setTedate(task.getTedate());
		oldTask.setTsdate(task.getTsdate());
	    oldTask.setTpriority(task.getTpriority());
	    oldTask.setPtask(task.getPtask());
		session.flush();
		return task.getTaskId();
	}

	
	@Override
	public void updateUser(String id, Task task) {
//		Session session =sessionFactory.getCurrentSession();
		Session session =entityManager.unwrap(Session.class);
		
		String hql = "from user where fname  = :employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("employee_id", id);
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			User oldUser =(User) itr.next();
			User oldUser1 =session.byId(User.class).load(oldUser.getUserId());
			String getTask=String.valueOf(task.getTaskId());
			String getProject=String.valueOf(task.getProjectId());
			oldUser1.setTid(getTask);
			oldUser1.setProjectid(getProject);
			session.flush();
		}
		
	}

	
	@Override
	public List<User> getAllUsers() {
//		List<User> l= sessionFactory.getCurrentSession().createQuery("from user").list();
		
		Session currentSession =entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		List<User> list = currentSession.createQuery("from user").list();
		return list;
	}


	@Override
	public void userDelete(int id) {
//		Session session =sessionFactory.getCurrentSession();
		Session session =entityManager.unwrap(Session.class);
		User oldTask =session.byId(User.class).load(id);
		session.delete(oldTask);
		
	}
	
	@Override
	public void updateUserwithId(int id, User user) {
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		User oldTask =session.byId(User.class).load(id);
		oldTask.setEid(user.getEid());
		oldTask.setFname(user.getFname());
		oldTask.setLname(user.getLname());
	
		session.flush();
	}
	

 	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers(int id) {

//		Session currentSession = entityManager.unwrap(Session.class);
//		List<Project> list = currentSession.createQuery("from project").list();
		
		List<User> list =null;
		
		if(id==1) {
//		 list= sessionFactory.getCurrentSession().createQuery("from user order by fname asc").list();
		 list= entityManager.unwrap(Session.class).createQuery("from user order by fname asc").list();			
		 return list;
		}
		else if(id==2) {
//			list= sessionFactory.getCurrentSession().createQuery("from user order by lname asc").list();
			list= entityManager.unwrap(Session.class).createQuery("from user order by lname asc").list();
			return list;
			}
		else if(id==3) {
//			 list= sessionFactory.getCurrentSession().createQuery("from user order by eid asc").list();
			 list= entityManager.unwrap(Session.class).createQuery("from user order by eid asc").list();
			 return list;
			}
		else {
//			 list= sessionFactory.getCurrentSession().createQuery("from user").list();
			 list= entityManager.unwrap(Session.class).createQuery("from user").list();
			 return list;
		}
	}

		
	@SuppressWarnings("rawtypes")
	@Override
	public List<Project> getProjects() throws ParseException {
		// TODO Auto-generated method stub
		//List<Project> l= sessionFactory.getCurrentSession().createQuery("from user").list();
		//return l;
		List<Project> l=new ArrayList<Project>();
		Project proj =new Project();
//		Session session =sessionFactory.getCurrentSession();
		Session session =entityManager.unwrap(Session.class);
		
		String hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount, a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority";
		Query query = session.createQuery(hql);
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			proj =new Project();
			Object[] object = (Object[]) itr.next();
			String client = String.valueOf(object[0]);
			proj.setTaskCount(Integer.parseInt(String.valueOf(object[0])));
			proj.setProject(String.valueOf(object[1]));
			String s=String.valueOf(object[2]);
			String s2=String.valueOf(object[3]);
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);  
			Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s); 
			proj.setEdate(date2);
			proj.setSdate(date1);
			proj.setPriority(Integer.parseInt(String.valueOf(object[4])));
			proj.setTcomCount(Integer.parseInt(String.valueOf(object[5])));
			proj.setProjectId(Integer.parseInt(String.valueOf(object[6])));
			l.add(proj);
		}
		return l;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Project> getProjectsOrder(int id) throws ParseException {
		// TODO Auto-generated method stub
		//List<Project> l= sessionFactory.getCurrentSession().createQuery("from user").list();
		//return l;
		List<Project> l=new ArrayList<Project>();
		Project proj =new Project();
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		String hql="";
		if(id==1) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by a.sdate asc";
		}
		else if(id==2) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by a.edate asc";
		}
		else if(id==3) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by a.priority asc";
		}
		else if(id==4) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by tcomCount asc";
		}
		else{
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority";
		}
		Query query = session.createQuery(hql);
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			proj =new Project();
			Object[] object = (Object[]) itr.next();
			String client = String.valueOf(object[0]);
			proj.setTaskCount(Integer.parseInt(String.valueOf(object[0])));
			proj.setProject(String.valueOf(object[1]));
			String s=String.valueOf(object[2]);
			String s2=String.valueOf(object[3]);
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);  
			Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s2); 
			proj.setEdate(date2);
			proj.setSdate(date1);
			proj.setPriority(Integer.parseInt(String.valueOf(object[4])));
			proj.setTcomCount(Integer.parseInt(String.valueOf(object[5])));
			proj.setProjectId(Integer.parseInt(String.valueOf(object[6])));
			l.add(proj);
		}
		return l;
	}
	
	
	@Override
	public void updateProjectwithId(int id, Project project) {
//		Session session =sessionFactory.getCurrentSession();
		Session session =entityManager.unwrap(Session.class);	
		Project oldTask =session.byId(Project.class).load(id);
		
		oldTask.setProject(project.getProject());
		oldTask.setSdate(project.getSdate());
		oldTask.setEdate(project.getEdate());
		oldTask.setPriority(project.getPriority());
		
		session.flush(); 		
		
//		if ( projectRepository.existsById(project.getProjectId())) {
//			throw new EntityNotFoundException("There is no entity with such ID in the database.");
//		}
//		projectRepository.save(project);
	}
	
	
	@Override
	public void endTask(int id) {
//		Session session =sessionFactory.getCurrentSession();
		Session session =entityManager.unwrap(Session.class);
		Task oldtask =session.byId(Task.class).load(id);
		oldtask.setStat("completed");
		session.flush();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Task> getTasks() throws ParseException {
		// TODO Auto-generated method stub
//		List<Task> l= sessionFactory.getCurrentSession().createQuery("from task").list();
		List<Task> l=new ArrayList<Task>();
		Task task =new Task();
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		Query query = session.createQuery("select b.taskId,b.ptask,b.pname,b.tname,b.tsdate,b.tedate,b.tpriority,a.eid  from user a, task b where a.projectid=b.projectId\r\n" + 
				"");
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			task =new Task();
			Object[] object = (Object[]) itr.next();
			
			String s=String.valueOf(object[4]);
			String s2=String.valueOf(object[5]);
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);  
			Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s2); 
			task.setEmpId(String.valueOf(object[7]));
			task.setPname(String.valueOf(object[2]));
			task.setPtask(String.valueOf(object[1]));
			task.setTaskId(Integer.parseInt(String.valueOf(object[0])));
			task.setTedate(date2);
			task.setTname(String.valueOf(object[3]));
			task.setTsdate(date1);
			task.setTpriority(String.valueOf(object[6]));
			
			l.add(task);
	
			 }
		return l;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasksOrder(int id) {

		List<Task> list =null;
		if(id==1) {
//		 list= sessionFactory.getCurrentSession().createQuery("from task order by tsdate asc").list();
		 list= entityManager.unwrap(Session.class).createQuery("from task order by tsdate asc").list();
		 return list;
		}
		else if(id==2) {
//			 list= sessionFactory.getCurrentSession().createQuery("from task order by tedate asc").list();
			 list= entityManager.unwrap(Session.class).createQuery("from task order by tedate asc").list();
			 return list;
			}
		else if(id==3) {
//			 list= sessionFactory.getCurrentSession().createQuery("from task  order by tpriority asc").list();
			 list= entityManager.unwrap(Session.class).createQuery("from task  order by tpriority asc").list(); 			
			 return list;
			}
		else if(id==4) {
//			list= sessionFactory.getCurrentSession().createQuery("from task where stat='completed' order by taskId asc").list();
			list= entityManager.unwrap(Session.class).createQuery("from task where stat='completed' order by taskId asc").list();
			return list;
			}
		else {
//			list= sessionFactory.getCurrentSession().createQuery("from task").list();
			list= entityManager.unwrap(Session.class).createQuery("from task").list();	
		    return list;
		}
	}
	
	
	
	@Override
	public List<Project> getAllProjects() {
		
//		List<Project> list= sessionFactory.getCurrentSession().createQuery("from project").list();
//		return list;
		
//		List<Project> list = (List<Project>) projectRepository.findAll();
//		return list;
		
		Session currentSession =entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		List<Project> list = currentSession.createQuery("from project").list();
		System.out.println("Projects Listed");
		return list;
	}
	
	
	@Override
	public List<ParentTask> getAllParentProjects() {
//		List<ParentTask> list= sessionFactory.getCurrentSession().createQuery("from parent").list();
//		return list;	
		
//		List<ParentTask> list = (List<ParentTask>) taskmanagerRepository.findAll();
//		return list;
		
		Session currentSession =entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		List<ParentTask> list = currentSession.createQuery("from parent").list();
		return list;
	}
	

	@Override
	public void deleteTask(int id) {
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		Task oldTask =session.byId(Task.class).load(id);
		session.delete(oldTask);
		
	}
	

	@Override
	public int getProjectIds(Task task) {
//		Session session =sessionFactory.getCurrentSession();
		
		Session session =entityManager.unwrap(Session.class);
		String hql = "from project where project  = :project";
		Query query = session.createQuery(hql);
		Project proj =null;
		query.setParameter("project", task.getPname());
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			proj =(Project) itr.next();
					}
		
		return proj.getProjectId();
		
	}

}