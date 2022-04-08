package com.robot.service;

import java.util.List;
import java.util.Map;

import com.robot.model.Robot;
import com.robot.model.Surviver;

public interface SurviverService {

	public Surviver createSurviver(Surviver surviver);
	
	public Surviver updateLocation(Long id,Surviver surviver);
	
	public Surviver markSurviverToZombie(Long id);
	
	public Map<String, String> findInfectedNonInfectedRatio();

	public List<Surviver> findAllInfectedNonSurvivers();

	public List<Surviver> findAllNonInfectedNonSurvivers();

	public List<Robot> findAllRobots();
}
