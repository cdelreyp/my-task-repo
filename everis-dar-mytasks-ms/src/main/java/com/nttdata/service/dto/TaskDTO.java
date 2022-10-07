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

	private Timestamp entry_date;

	private Timestamp modified_date;

	private Timestamp cancel_date;

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
