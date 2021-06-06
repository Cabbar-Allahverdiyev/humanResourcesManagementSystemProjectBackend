package com.example.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobPostingService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.JobPostingDao;
import com.example.hrms.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements JobPostingService{
	
	private JobPostingDao jobPostingDao;
	
	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao) {
		super();
		this.jobPostingDao = jobPostingDao;
	}

	@Override
	public DataResult<List<JobPosting>> getByActivJobsAdverts() {
		
		return  new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getByActivJobsAdverts(),Messages.aktivJobsAdvertsListed);
	}

	@Override
	public DataResult<List<JobPosting>> getAll() {
		return  new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(),Messages.jobPostingsListed);
	}
	
	
	@Override
	public DataResult<List<JobPosting>> getByActiveTrueOrderByStartApplicationDate() {
		//Sort  sort =Sort.by(Sort.Direction.ASC,"startApplicationDate");
		return  new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getByIsActiveTrueOrderByStartApplicationDate(),Messages.jobPostingsActivatedAndStartApplicationDateListed);
	}
	
	@Override
	public DataResult<List<JobPosting>> getByIsActiveTrueAndEmloyerId(int employerId) {
		return  new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getByIsActiveTrueAndEmployer_Id(employerId),Messages.jobPostingsActivatedAndByEmployerIdListed);

	}
	

	@Override
	public Result add(JobPosting jobPosting) {
		Result rules=BusinessRules.run();

		if (rules!=null) {
			return rules;
		}
		
		
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult(Messages.jobPostingAdded);
	}

	

	


}
