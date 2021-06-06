package com.example.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {
	List<JobSeeker> findByEmail(String email);
	List<JobSeeker> findByIdentityNumber(String identityNumber);

}
