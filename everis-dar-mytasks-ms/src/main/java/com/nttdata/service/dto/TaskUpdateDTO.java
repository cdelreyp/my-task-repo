package com.nttdata.service.dto;

import javax.validation.constraints.Size;

import com.nttdata.utils.enums.enumStatus;

public class TaskUpdateDTO {
	
	private enumStatus status;

	@Size(max = 256)
	private String description;
	
	@Size(max = 16)
	private String userAsigned;

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

	public String getUserAsigned() {
		return userAsigned;
	}

	public void setUserAsigned(String userAsigned) {
		this.userAsigned = userAsigned;
	}


}
