package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobCategoryService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.JobCategoryDao;
import com.example.hrms.entities.concretes.JobCategory;

@Service
public class JobCategoryManager implements JobCategoryService{
	
	private JobCategoryDao jobCategoyDao;
	
	@Autowired
	public JobCategoryManager(JobCategoryDao jobCategoyDao) {
		super();
		this.jobCategoyDao = jobCategoyDao;
	}

	@Override
	public DataResult< List<JobCategory>> getAll() {
		
		return new SuccessDataResult<List<JobCategory>>(this.jobCategoyDao.findAll(),Messages.jobCategoryListed);
	}

	@Override
	public Result add(JobCategory jobCategory) {
		Result rules=BusinessRules.run(isThereJobCategory(jobCategory.getCategoryName()));

		if (rules!=null) {
			return rules;
		}
		
		this.jobCategoyDao.save(jobCategory);
		return new SuccessResult(Messages.jobCategoryAdded);
	}
	
	//Business Rules Methods
	
	private Result isThereJobCategory(String jobCategory) {
		List<JobCategory> result=this.jobCategoyDao.findByCategoryName(jobCategory);
		if (result.stream().count()!=0) {
			return new ErrorResult(Messages.jobCategoryAvailable);
		}
		return new SuccessResult();
	}
	
	

}
