package com.wssAngularProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wssAngularProject.models.Carpet;



@Repository
public interface CarpetRepository extends JpaRepository<Carpet, Integer>{
	
	Carpet findByNombre(String nombre);

}	