package com.example.hrms.entities.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingAddDto {
	@JsonIgnore
	private int id;
	private int employerId;
	private int jobId;
	private int openPositionNumber;
	private int minSalary;
	private int maxSalary;
	private int cityId;
	private LocalDate createdDate;
	private LocalDate closedDate;
	private String description;
}
