package com.example.hrms.entities.concretes;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.hrms.entities.abstracts.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobPostings"})
public class Job implements IEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	/*
	 * @Column(name="job_category_id") 
	 * private int jobCategoryId;
	 */
	
	@Column(name="job_name")
	private String jobName;
	
	@ManyToOne()
	@JoinColumn(name="job_category_id")
	private JobCategory jobCategory;
	
	
	@OneToMany(mappedBy = "job")
	private List<JobPosting> jobPostings;
	
	@OneToMany(mappedBy = "job")
	private List<JobExperience> jobExperiences;
}

