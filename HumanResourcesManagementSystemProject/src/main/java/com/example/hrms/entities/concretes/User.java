package com.example.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



import lombok.Data;

@Data
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	
	@JsonIgnore
	@Column(name = "verify")
	private boolean verify=false;
	

	@Email
	@Column(name="email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	private String passwordRepeat;
}
