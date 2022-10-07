package com.nttdata.repository.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.nttdata.utils.enums.enumStatus;

@Entity
@Table(name="tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "status")
	private enumStatus status;

	@Column(name = "description")
	@Size(max=256)
	private String description;
	
	@Column(name = "user_creator")
	@Size(max=16)
	private String userCreator;
	
	@Column(name = "user_asigned")
	@Size(max=16)
	private String userAsigned;

	@Column(name = "entry_date")
	private Timestamp entry_date;

	@Column(name = "modified_date")
	private Timestamp modified_date;

	@Column(name = "cancel_date")
	private Timestamp cancel_date;
	
	public Task() {
		
	}

	public Task(Long id, String description, enumStatus status, String userCreator) {
		this.id = id;
		this.description = description;
		this.status = status;
		this.userCreator = userCreator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public enumStatus getStatus() {
		return status;
	}

	public void setStatus(enumStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(String userCreator) {
		this.userCreator = userCreator;
	}

	public String getUserAsigned() {
		return userAsigned;
	}

	public void setUserAsigned(String userAsigned) {
		this.userAsigned = userAsigned;
	}

	public Timestamp getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Timestamp entry_date) {
		this.entry_date = entry_date;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}

	public Timestamp getCancel_date() {
		return cancel_date;
	}

	public void setCancel_date(Timestamp cancel_date) {
		this.cancel_date = cancel_date;
	}

}
