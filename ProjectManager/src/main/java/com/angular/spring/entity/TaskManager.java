package com.angular.spring.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="TaskManager")
public class TaskManager {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;
	private String parentId;
	private String task;

	@Transient
	private String PriorityFrom;
	public String getPriorityFrom() {
		return PriorityFrom;
	}
	public void setPriorityFrom(String priorityFrom) {
		PriorityFrom = priorityFrom;
	}
	public String getPriorityto() {
		return Priorityto;
	}
	public void setPriorityto(String priorityto) {
		Priorityto = priorityto;
	}
	@Transient
	private String Priorityto;
	@Basic
	//@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private int priority;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/*public String getTask() {
		return Task;
	}
	public void setTask(String task) {
		this.Task = task;
	}*/
	/*public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}*/
	
	public int getPriority() {
		return priority;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

}