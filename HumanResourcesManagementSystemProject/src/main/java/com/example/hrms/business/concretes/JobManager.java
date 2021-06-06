package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.JobDao;
import com.example.hrms.entities.concretes.Job;

@Service
public class JobManager implements JobService{
	private JobDao jobDao;
	
	@Autowired
	public JobManager(JobDao jobDao) {
		super();
		this.jobDao = jobDao;
	}

	@Override
	public DataResult< List<Job>> getAll() {
		return new SuccessDataResult<List<Job>>( this.jobDao.findAll(),Messages.jobListed);
	}

	@Override
	public Result add(Job job) {
		
		Result rules=BusinessRules.run(
				isThereJob(job.getJobName())
				);
		
		if (rules!=null) {
			return rules;
		}
		
		this.jobDao.save(job);
		return new SuccessResult(Messages.jobAdded);
	}
	
	
	
	//Business Rules Methods
	private Result isThereJob(String jobName) {
		List<Job> result=this.jobDao.findByJobName(jobName);
		if (result.stream().count()!=0) {
			return new ErrorResult(Messages.thisJobAvailable);
		}
		return  new SuccessResult();
	}
	
}
