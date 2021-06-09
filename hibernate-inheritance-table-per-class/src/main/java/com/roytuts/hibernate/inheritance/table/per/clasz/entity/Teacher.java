package com.roytuts.hibernate.inheritance.table.per.clasz.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sub")
public class Teacher extends Person {

	@Column(name = "subject")
	private String subject;

	public Teacher() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
