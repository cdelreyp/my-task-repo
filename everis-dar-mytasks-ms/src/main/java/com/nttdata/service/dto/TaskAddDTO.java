package com.nttdata.service.dto;

import javax.validation.constraints.Size;

import com.nttdata.utils.enums.enumStatus;

public class TaskAddDTO {

	private enumStatus status;

	@Size(max = 256)
	private String description;

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

}
