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

@Entity(name="task")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;
	private String ptask;
	private String pname;
	private String tname;

	@Basic
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tsdate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tedate;
	
	private String tpriority;
	
	private String stat;
	
	@Transient
	private String empId;
	
	private int projectId;
	
	@Transient
	private String pcheck;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public String getPtask() {
		return ptask;
	}
	public void setPtask(String ptask) {
		this.ptask = ptask;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public Date getTsdate() {
		return tsdate;
	}
	public void setTsdate(Date tsdate) {
		this.tsdate = tsdate;
	}
	
	public Date getTedate() {
		return tedate;
	}
	public void setTedate(Date tedate) {
		this.tedate = tedate;
	}
	
	public String getTpriority() {
		return tpriority;
	}	
	public void setTpriority(String tpriority) {
		this.tpriority = tpriority;
	}
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public String getPcheck() {
		return pcheck;
	}
	public void setPcheck(String pcheck) {
		this.pcheck = pcheck;
	}	
		
}