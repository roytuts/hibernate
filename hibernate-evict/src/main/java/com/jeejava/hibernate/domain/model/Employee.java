package com.jeejava.hibernate.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String designation;
	private String joiningdate;
	private String phone;

	public Employee() {

	}

	public Employee(String name, String designation, String joiningdate, String phone) {
		this.name = name;
		this.designation = designation;
		this.joiningdate = joiningdate;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Joining Date: "
				+ joiningdate + ", Phone: " + phone;
	}

}