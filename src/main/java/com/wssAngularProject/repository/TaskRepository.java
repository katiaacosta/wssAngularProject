package com.wssAngularProject.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wssAngularProject.models.Carpet;
import com.wssAngularProject.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	//List<Task> findByIdCarpet(int idCarpet);
	List<Task> findByCarpet(Carpet carpet, Sort sort);

}	