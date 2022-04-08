package com.robot.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robot.exception.ResourceNotFoundException;
import com.robot.model.Robot;
import com.robot.model.Surviver;
import com.robot.service.SurviverService;

@RestController
@RequestMapping("/robot/surviver")
public class RobotApocalypseController {

	@Autowired
	SurviverService surviverService;

	@PostMapping("/create")
	public ResponseEntity<Surviver> createSurviver(@RequestBody Surviver surviver) {
		try {			
			Surviver response = surviverService.createSurviver(surviver);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/updateLocation/{id}")
	public ResponseEntity<Surviver> updateLocation(@PathVariable(value = "id") Long id,
			@RequestBody Surviver surviver) throws Exception {
		Surviver updatedSurviver = surviverService.updateLocation(id, surviver);
		if (updatedSurviver==null) {
				throw new ResourceNotFoundException("Surviver not found!");
		}
		return new ResponseEntity<>(updatedSurviver, HttpStatus.OK);
	}
	
	@PutMapping("/markInfected/{id}")
	public ResponseEntity<Surviver> markSurviverToZombie(@PathVariable(value = "id") Long id
			) throws Exception {
		Surviver surviver = surviverService.markSurviverToZombie(id);
		if (surviver==null) {
			throw new ResourceNotFoundException("Surviver not found!");
	}
		return new ResponseEntity<>(surviver, HttpStatus.OK);
	}
	
	@GetMapping("/percentage")
	public ResponseEntity<Map<String, String>> findInfectedNonInfectedRatio() {
		Map<String, String> surviver = surviverService.findInfectedNonInfectedRatio();
		return new ResponseEntity<>(surviver, HttpStatus.OK);
	}
	
	@GetMapping("/infected")
	public ResponseEntity<List<Surviver>> findAllInfectedNonSurvivers() {
		List<Surviver> surviver = surviverService.findAllInfectedNonSurvivers();
		return new ResponseEntity<>(surviver, HttpStatus.OK);
	}
	
	@GetMapping("/non-infected")
	public ResponseEntity<List<Surviver>> findAllNonInfectedNonSurvivers() {
		List<Surviver> surviver = surviverService.findAllNonInfectedNonSurvivers();
		return new ResponseEntity<>(surviver, HttpStatus.OK);
	}
	@GetMapping("/robots")
	public ResponseEntity<List<Robot>> findAllRobots() {
		List<Robot> surviver = surviverService.findAllRobots();
		return new ResponseEntity<>(surviver, HttpStatus.OK);
	}
}
