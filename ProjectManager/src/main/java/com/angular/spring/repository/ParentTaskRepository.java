package com.angular.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angular.spring.entity.ParentTask;


public interface ParentTaskRepository extends CrudRepository <ParentTask, Integer>{

}
