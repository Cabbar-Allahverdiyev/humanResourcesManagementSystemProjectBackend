package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.JobPosting;

public interface JobPostingService {
	
	DataResult<List<JobPosting>> getAll(); 
	
	DataResult<List<JobPosting>> getByActivJobsAdverts();
	
	DataResult<List<JobPosting>> getByActiveTrueOrderByStartApplicationDate();
	
	DataResult<List<JobPosting>> getByIsActiveTrueAndEmloyerId(int employerId);
	
	Result add(JobPosting jobPosting);
}
