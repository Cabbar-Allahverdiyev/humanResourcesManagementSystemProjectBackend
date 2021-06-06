package com.example.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingGetDto {
	private String employerCompanyName;
	private String jobName;
	private int openPositionNumber;
	private LocalDate createdDate;
	private LocalDate closedDate;
	private String description;
}
