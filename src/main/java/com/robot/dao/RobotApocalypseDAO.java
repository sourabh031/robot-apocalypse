package com.robot.dao;

import java.util.List;
import java.util.Optional;

import com.robot.model.Surviver;


public interface RobotApocalypseDAO {

	public Surviver saveSurviver(Surviver surviver);
	
	public Surviver updateSurviver(Surviver surviver);
	
	public Optional<Surviver> findById(Long id) throws Exception;
	
	public List<Surviver> findAllSurviver();
}
