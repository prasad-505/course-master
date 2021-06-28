package com.accolite.course.models;

public class Student {
	Long id;
	String name;
	String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + ", email=" + email + "]";
	}


}
