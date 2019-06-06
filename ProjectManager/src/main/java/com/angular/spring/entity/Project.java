package com.angular.spring.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id") //newly added
	private int projectId;

	private String project;

	@Basic
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date sdate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date edate;

	private int priority;

	@Transient
	private int taskCount;
	
	@Transient
	private int tcomCount;
		
	

	public int getTcomCount() {
		return tcomCount;
	}

	public void setTcomCount(int tcomCount) {
		this.tcomCount = tcomCount;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", project=" + project + ", sdate=" + sdate + ", edate=" + edate
				+ ", priority=" + priority + ", taskCount=" + taskCount + ", tcomCount=" + tcomCount + "]";
	}
	
}