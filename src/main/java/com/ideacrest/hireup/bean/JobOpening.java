package com.ideacrest.hireup.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class JobOpening {

	@NotNull
	@Min(1)
	private long _id;

	@NotNull
	@Min(1)
	private long createdBy;

	@NotBlank
	private String openingDescription;

	@NotBlank
	private String startDate;

	@NotBlank
	private String endDate;

	@NotBlank
	private String expectedSalary;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public String getOpeningDescription() {
		return openingDescription;
	}

	public void setOpeningDescription(String openingDescription) {
		this.openingDescription = openingDescription;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

}
