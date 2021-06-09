package com.roytuts.hibernate.inheritance.table.per.clasz.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("yr")
public class Student extends Person {

	@Column(name = "year")
	private String year;

	public Student() {
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
