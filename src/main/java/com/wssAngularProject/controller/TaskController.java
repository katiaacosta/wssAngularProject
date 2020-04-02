package com.wssAngularProject.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wssAngularProject.exception.ResourceNotFoundException;
import com.wssAngularProject.repository.CarpetRepository;
import com.wssAngularProject.repository.TaskRepository;
import com.wssAngularProject.models.Carpet;
import com.wssAngularProject.models.Task;
import org.springframework.data.domain.Sort;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tareas")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private CarpetRepository carpetRepository;

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskRepository.findAll();

	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") int taskId) throws ResourceNotFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
		return ResponseEntity.ok().body(task);
	}

	@PostMapping("/tasks")
	public Task createTasks(@Valid @RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable(value = "id") int taskId,
			@Valid @RequestBody Task taskDetails) throws ResourceNotFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
		task.setCarpet(taskDetails.getCarpet());
		task.setNombre(taskDetails.getNombre());
		task.setPendiente(taskDetails.isPendiente());
		final Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/tasks/{id}")
	public Map<String, Boolean> deleteTask(@PathVariable(value = "id") int taskId) throws ResourceNotFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@GetMapping("/tasks/taskById/{id}")
	public ResponseEntity<List<Task>> getTaskByIdCarpet(@PathVariable(value = "id") int idCarpet) throws ResourceNotFoundException{
		
		Carpet carpet = carpetRepository.findById(idCarpet)
				.orElseThrow(() -> new ResourceNotFoundException("Carpet not found for this id :: " + idCarpet));
		
		
		List<Task> task = taskRepository.findByCarpet(carpet, Sort.by(Sort.Direction.ASC, "id"));
		return ResponseEntity.ok().body(task);
	}


}
