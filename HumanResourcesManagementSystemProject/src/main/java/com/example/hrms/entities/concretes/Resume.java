package com.example.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobPostings"})

@Entity
@Table(name="resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(targetEntity = JobSeeker.class)
	@JoinColumn(name = "job_seeker_id",nullable = false,referencedColumnName = "user_id")
	private JobSeeker  jobSeeker;
	
	@Column(name="github_link")
	private String githubLink;
	
	@Column(name="linkedin_link")
	private String linkedinLink;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="description")
	private String description;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;
	
	@Column(name="is_active")
	private boolean isActive=true;
	
	
	
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL ) 
	private List<Language> languages;
	  
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL ) 
	private List<Technology> technologies;
	  
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL ) 
	private List<Education> education;
	 
	@OneToMany(mappedBy = "resume",cascade = CascadeType.ALL ) 
	private List<JobExperience> jobExperiences;
	
}

