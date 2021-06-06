package com.example.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import com.example.hrms.entities.abstracts.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="employers")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobPostings"})
public class Employer implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank
	@NotNull
	@Column(name = "company_name")
	private String companyName;
	
	@NotBlank
	@NotNull
	@Column(name = "website")
	private String website;
	
	
	
	@Column(name = "email")
	@NotBlank
	@NotNull
	@Email
	private String email;
	
	
	
	@Column(name = "phone_number")
	@NotBlank
	@NotNull
	private String phoneNumber;
	
	@Column(name = "password")
	@NotBlank
	@NotNull
	private String password;
	
	private String passwordRepeat;
	
	@OneToMany(mappedBy = "employer")
	List<JobPosting> jobPostings;

	
}
