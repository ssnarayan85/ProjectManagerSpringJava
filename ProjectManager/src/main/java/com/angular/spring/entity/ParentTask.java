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

@Entity(name="parent")
public class ParentTask {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_id")
	private int pid;
	
	@Column(name="parent_task")
	private String ptask;
	
	
	public ParentTask(int pid, String ptask) {
		super();
		this.pid = pid;
		this.ptask = ptask;
	}
	
	public ParentTask() {
		super();
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPtask() {
		return ptask;
	}
	public void setPtask(String ptask) {
		this.ptask = ptask;
	}	
}