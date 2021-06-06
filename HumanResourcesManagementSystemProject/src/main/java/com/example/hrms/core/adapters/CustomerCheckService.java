package com.example.hrms.core.adapters;

import com.example.hrms.entities.concretes.JobSeeker;

public interface CustomerCheckService {
	boolean chekIfRealPerson(JobSeeker jobSeeker);
}

