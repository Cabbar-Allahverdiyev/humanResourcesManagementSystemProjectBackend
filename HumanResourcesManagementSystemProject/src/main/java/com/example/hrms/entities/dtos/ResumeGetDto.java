package com.example.hrms.entities.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.hrms.entities.concretes.Education;
import com.example.hrms.entities.concretes.JobExperience;
import com.example.hrms.entities.concretes.Language;
import com.example.hrms.entities.concretes.Technology;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeGetDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private int id;
	private int jobSeekerId;
	private String githubLink;
	private String linkedLink;
	private String photo;
	private String description;
	private LocalDate createdDate;
	private LocalDate updatedDate;
//	private List<JobExperience> jobExperiences;
//	private List<Language> languages;
//	private List<Technology> technologies;
//	private List<Education> education;
}
