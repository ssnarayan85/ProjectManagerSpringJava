package com.angular.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.angular.spring.entity.ParentTask;
import com.angular.spring.entity.Project;


public interface ProjectRepository extends CrudRepository <Project, Integer>{

}