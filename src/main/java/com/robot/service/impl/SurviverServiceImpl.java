package com.robot.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.robot.dao.RobotApocalypseDAO;
import com.robot.model.Robot;
import com.robot.model.Surviver;
import com.robot.service.SurviverService;

@Service
public class SurviverServiceImpl implements SurviverService {

	@Autowired
	RobotApocalypseDAO surviverDAO;

	@Autowired
	RestTemplate restTemplate;

	public Surviver createSurviver(Surviver surviver) {
		return surviverDAO.saveSurviver(surviver);
	}

	@Override
	public Surviver updateLocation(Long id, Surviver surviver) {
		Optional<Surviver> existingSurviver = null;
		Surviver surviverDetails = null;
		try {
			existingSurviver = surviverDAO.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (existingSurviver.isPresent()) {
			surviverDetails=existingSurviver.get();
			surviverDetails.setLocation(surviver.getLocation());
		return surviverDAO.updateSurviver(surviverDetails);
		}
		return surviverDetails;
	}

	@Override
	public Surviver markSurviverToZombie(Long id) {
		Optional<Surviver> existingSurviver = null;
		Surviver surviverDetails = null;
		try {
			existingSurviver = surviverDAO.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (existingSurviver.isPresent()) {
			surviverDetails=existingSurviver.get();
			surviverDetails.setInfected(true);
		return surviverDAO.updateSurviver(surviverDetails);
		}
		return surviverDetails;
	}

	@Override
	public Map<String, String> findInfectedNonInfectedRatio() {
		Long infectedCount;
		Long nonInfecteCount;
		Long totalCount;
		Map<String, String> analysis = new HashMap<>();
		List<Surviver> listSurvivers = surviverDAO.findAllSurviver();
		if (!CollectionUtils.isEmpty(listSurvivers)) {
			infectedCount = listSurvivers.stream().filter(surviver -> surviver.isInfected()).count();
			nonInfecteCount = listSurvivers.stream().filter(surviver -> !surviver.isInfected()).count();
			totalCount = listSurvivers.stream().count();
			if (totalCount == 0) {
				totalCount = 1L;
			}
			analysis.put("Infected Surviver", String.valueOf((infectedCount * 100 / totalCount)) + "%");
			analysis.put("Non-Infected Surviver", String.valueOf((nonInfecteCount * 100 / totalCount)) + "%");
		}
		return analysis;
	}

	@Override
	public List<Surviver> findAllInfectedNonSurvivers() {
		List<Surviver> listSurvivers = surviverDAO.findAllSurviver();
		if (!CollectionUtils.isEmpty(listSurvivers)) {
			listSurvivers = listSurvivers.stream().filter(surviver -> surviver.isInfected())
					.collect(Collectors.toList());
		}
		return listSurvivers;
	}

	@Override
	public List<Surviver> findAllNonInfectedNonSurvivers() {
		List<Surviver> listSurvivers = surviverDAO.findAllSurviver();
		if (!CollectionUtils.isEmpty(listSurvivers)) {
			listSurvivers = listSurvivers.stream().filter(surviver -> !surviver.isInfected())
					.collect(Collectors.toList());
		}
		return listSurvivers;
	}

	@Override
	public List<Robot> findAllRobots() {
		ResponseEntity<List<Robot>> rateResponse = restTemplate.exchange(
				"https://robotstakeover20210903110417.azurewebsites.net/robotcpu", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Robot>>() {
				});
		List<Robot> robotList = rateResponse.getBody().stream()
				.sorted(Comparator.comparing(Robot::getCategory)
				.thenComparing(Robot::getModel))
				.collect(Collectors.toList());
		
		return robotList != null ? robotList : new ArrayList<>();
	}
}
