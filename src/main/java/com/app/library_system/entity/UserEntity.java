package com.app.library_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long UID;
	
	public String name;
	
	public long phn_no;
	
	public String email_id;
	
	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public long getUID() {
		return UID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhn_no() {
		return phn_no;
	}

	public void setPhn_no(long phn_no) {
		this.phn_no = phn_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
}
