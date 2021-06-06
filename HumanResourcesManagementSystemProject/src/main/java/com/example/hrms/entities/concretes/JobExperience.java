package com.example.hrms.entities.concretes;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resume_job_exp")
public class JobExperience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JsonProperty(access = Access.WRITE_ONLY)  //nezer et
	@ManyToOne(targetEntity = Resume.class)
	@JoinColumn(name="resume_id")
	private Resume resume;
	
	@ManyToOne(targetEntity = Job.class)  //nezer et
	@JoinColumn(name="job_id",referencedColumnName = "id",nullable = false)
	private Job job;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="started_date")
	private LocalDate startedDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="created_date")
	private LocalDate createdDate;

	
	

}

