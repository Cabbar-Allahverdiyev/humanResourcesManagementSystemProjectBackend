package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	Result add(JobSeeker jobSeeker);
	DataResult<List<JobSeeker>> getAll();
	DataResult<List<JobSeeker>> getByEmail(String email);
}
