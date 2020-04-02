package com.wssAngularProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wssAngularProject.exception.ResourceNotFoundException;
import com.wssAngularProject.models.Carpet;
import com.wssAngularProject.repository.CarpetRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/carpetas")
public class CarpetController {
	
	@Autowired
	private CarpetRepository carpetRepository;

	@GetMapping("/carpets")
	public List<Carpet> getAllCarpets() {
		return carpetRepository.findAll();
	}

	@GetMapping("/carpets/{id}")
	public ResponseEntity<Carpet> getCarpetById(@PathVariable(value = "id") int carpetId) throws ResourceNotFoundException {
		Carpet carpet = carpetRepository.findById(carpetId)
				.orElseThrow(() -> new ResourceNotFoundException("Carpet not found for this id :: " + carpetId));
		return ResponseEntity.ok().body(carpet);
	}

	@PostMapping("/carpets")
	public Carpet createCarpets(@Valid @RequestBody Carpet carpet) {
		return carpetRepository.save(carpet);
	}

	@PutMapping("/carpets/{id}")
	public ResponseEntity<Carpet> updateCarpet(@PathVariable(value = "id") int carpetId,
			@Valid @RequestBody Carpet carpetDetails) throws ResourceNotFoundException {
		Carpet carpet = carpetRepository.findById(carpetId)
				.orElseThrow(() -> new ResourceNotFoundException("Carpet not found for this id :: " + carpetId));

		carpet.setId(carpetDetails.getId());
		carpet.setNombre(carpetDetails.getNombre());
		final Carpet updatedCarpet = carpetRepository.save(carpet);
		return ResponseEntity.ok(updatedCarpet);
	}

	@DeleteMapping("/carpets/{id}")
	public Map<String, Boolean> deleteCarpet(@PathVariable(value = "id") int carpetId) throws ResourceNotFoundException {
		Carpet carpet = carpetRepository.findById(carpetId)
				.orElseThrow(() -> new ResourceNotFoundException("Carpet not found for this id :: " + carpetId));

		carpetRepository.delete(carpet);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}


