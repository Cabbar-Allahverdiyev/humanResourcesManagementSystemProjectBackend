package com.example.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.hrms.entities.abstracts.IEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="job_postings")
public class JobPosting implements IEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(targetEntity = Job.class,fetch = FetchType.LAZY,optional = false) //fetch-e nezaret et
	@JoinColumn(name = "job_id",referencedColumnName = "id",nullable = false)
	private Job job;
	
	@ManyToOne(targetEntity =City.class,fetch = FetchType.LAZY,optional = false)  //fetch-e nezaret et
	@JoinColumn(name = "city_id",referencedColumnName = "id",nullable = false)
	private City city;
	
	@ManyToOne(targetEntity = Employer.class,fetch = FetchType.LAZY,optional = false)//fetch-e nezaret et
	@JoinColumn(name="employer_id",referencedColumnName = "id",nullable = false)
	private Employer employer;
	
	@Column(name = "min_salary")
	private int minSalary;
	
	@Column(name = "max_salary")
	private int maxSalary;
	
	@Column(name = "open_position_number")
	private int openPositionNumber;
	
	@Column(name = "start_application_date")
	private LocalDate startApplicationDate;
	
	@Column(name = "application_deadline")
	private LocalDate applicationDeadline;
	
	@Column(name = "job_description")
	private String jobDescription;
	
	@Column(name="is_active",nullable = false)
	private boolean isActive=true;
	
	

}

