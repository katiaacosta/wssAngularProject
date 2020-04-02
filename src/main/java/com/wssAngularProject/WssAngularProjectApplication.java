package com.wssAngularProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wssAngularProject.models.Carpet;
import com.wssAngularProject.models.Task;
import com.wssAngularProject.repository.CarpetRepository;
import com.wssAngularProject.repository.TaskRepository;

@SpringBootApplication
public class WssAngularProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WssAngularProjectApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner mappingDemo(CarpetRepository carpetRepository,
                                         TaskRepository taskRepository) {
        return args -> {

            // create a new carpet
            Carpet carpet = new Carpet("Cosas que hacer");
            

            // save the carpet
            carpetRepository.save(carpet);
            
           Carpet c= carpetRepository.findByNombre("Cosas que hacer");

            // create and save new task
            taskRepository.save(new Task("hacer compras", false, c));
            taskRepository.save(new Task("ir al cajero", true, c));
            taskRepository.save(new Task("ir a la bicicleteria", false, null));
        };
    }

}
