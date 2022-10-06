package com.nttdata.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import utils.enumStatus;

@Entity
@Table(name="tasks")
public class Task {

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "status")
	private enumStatus status;

	@Column(name = "description")
	@Size(max=256)
	private String description;

	@Column(name = "entry_date")
	private Timestamp entry_date;

	@Column(name = "modified_date")
	private Timestamp modified_date;

	@Column(name = "cancel_date")
	private Timestamp cancel_date;

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
