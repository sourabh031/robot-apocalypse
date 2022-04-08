package com.robot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "survivers")
@DynamicUpdate
public class Surviver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "location")
	private String location;

	@ElementCollection
	@CollectionTable(name = "surviver_resources", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "name")
	private Set<String> resources = new HashSet<>();

	@Column(name = "isInfected")
	private boolean isInfected;

	public Set<String> getResources() {
		return resources;
	}

	public void setResources(Set<String> resources) {
		this.resources = resources;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public Surviver() {

	}

	public Surviver(String name, Integer age, String gender, String location, Set<String> resources,
			boolean isInfected) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.location = location;
		this.resources = resources;
		this.isInfected = isInfected;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
