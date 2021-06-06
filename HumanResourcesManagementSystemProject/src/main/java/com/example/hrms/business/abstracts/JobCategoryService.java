package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.JobCategory;

public interface JobCategoryService {
	DataResult<List<JobCategory>> getAll();
	Result add(JobCategory jobCategory);
}
