package com.example.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hrms.entities.concretes.JobPosting;

public interface JobPostingDao  extends JpaRepository<JobPosting, Integer>{
	
	List<JobPosting> getByIsActiveTrueOrderByStartApplicationDate();
	
	List<JobPosting> getByIsActiveTrueAndEmployer_Id(int employerId);
	
	@Query("From JobPosting where isActive=true")
	List<JobPosting> getByActivJobsAdverts();
	
//	 @Transactional
//	    @Modifying
//	    @Query("update JobAdvertisement j set j.active=:active where j.id=:id")
//	    void updateActive(boolean active, int id);
//	
//	@Query("From JobPosting where isActive=true and startApplicationDate=:startApplicationDate")
//	List<JobPosting> getByActivJobsAndByStartApplicationDate();
}
