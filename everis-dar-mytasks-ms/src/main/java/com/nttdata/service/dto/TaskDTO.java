package com.nttdata.service.dto;

import java.sql.Timestamp;

import com.nttdata.utils.enums.enumStatus;

/**
 * Class that models the DTO object of Task
 */
public class TaskDTO {

	private long id;

	private enumStatus status;

	private String description;

	private String userCreator;

	private String userAsigned;

	private Timestamp entryDate;

	private Timestamp modifiedDate;

	private Timestamp cancelDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
