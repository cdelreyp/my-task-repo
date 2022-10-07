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

/**
 * Class that models the Task entity
 */
@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "status")
	private enumStatus status;

	@Column(name = "description")
	@Size(max = 256)
	private String description;

	@Column(name = "user_creator")
	@Size(max = 16)
	private String userCreator;

	@Column(name = "user_asigned")
	@Size(max = 16)
	private String userAsigned;

	@Column(name = "entry_date")
	private Timestamp entryDate;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	@Column(name = "cancel_date")
	private Timestamp cancelDate;

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

	public Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Timestamp getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}

}
