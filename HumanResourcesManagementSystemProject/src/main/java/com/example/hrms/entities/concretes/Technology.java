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

import com.example.hrms.entities.abstracts.IEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "resume_techs")
@NoArgsConstructor
@AllArgsConstructor
public class Technology implements IEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JsonProperty(access = Access.WRITE_ONLY)  //nezer et
	@ManyToOne(targetEntity = Resume.class)
	@JoinColumn(name = "resume_id")
	private Resume resume;
	
	@Column(name="description")
	private String description;
	
	@Column(name="created_date")
	private LocalDate createdDate;

}

