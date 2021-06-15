package com.example.hrms.entities.concretes;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.hrms.entities.abstracts.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobPostings"})

@Entity
@Data
@Table(name = "edu_graduates")
@NoArgsConstructor
@AllArgsConstructor
public class Graduate implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
//	@OneToMany(mappedBy = "graduate")
//	private List<Education> educations;
}
