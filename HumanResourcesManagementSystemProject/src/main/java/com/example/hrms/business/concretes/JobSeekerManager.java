package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.JobSeekerService;
import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.business.abstracts.VerifyCodeService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.JobSeekerDao;
import com.example.hrms.entities.concretes.JobSeeker;
import com.example.hrms.business.rules.BusinessRulesMethods;



@Service
public class JobSeekerManager implements JobSeekerService{ 
	
	private JobSeekerDao jobSeekerDao;
	private UserService userService;
	private VerifyCodeService verifyCodeService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao,
							UserService userService,
							VerifyCodeService verifyCodeService
							) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.userService=userService;
		this.verifyCodeService=verifyCodeService;
	}

	@Override
	public DataResult<List<JobSeeker>>  getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(),Messages.jobSeekerListed) ;
	}

	@Override
	public Result add(JobSeeker jobSeeker) {		
		
		Result rules=BusinessRules.run(
				isEmailNowAvailable(jobSeeker.getEmail()),
				isIdentityNumberNowAvailable(jobSeeker.getIdentityNumber()),
				BusinessRulesMethods.passwordRepeatCompatibilityWithPassword(jobSeeker.getPassword(), jobSeeker.getPasswordRepeat())
			);
	
		if (rules!=null) {
			return rules;
		}
		
		this.jobSeekerDao.save(jobSeeker);
		this.verifyCodeService.createVerifyCode(this.userService.getOne(jobSeeker.getId()).getData());
		this.verifyCodeService.sendEmail(jobSeeker.getEmail());
		return new SuccessResult(Messages.jobSeekerAdded);
		
	}
	
	@Override
	public DataResult<List<JobSeeker>> getByEmail(String email) {
		
		return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findByEmail(email));
		
	}
	
	
	
	
	
	//Businnes rules methods
	
	private Result isEmailNowAvailable(String email) {
		List<JobSeeker> result=this.jobSeekerDao.findByEmail(email);
		if (result.stream().count()!=0) {
			return new ErrorResult(Messages.thisEmailIsAvailable);
		}
		return new SuccessResult();
	}
	
	private Result isIdentityNumberNowAvailable(String identityNumber) {
		List<JobSeeker> result=this.jobSeekerDao.findByIdentityNumber(identityNumber);
		if (result.stream().count()!=0) {
			return new ErrorResult(Messages.thisIdentityNumberIsAvailable);
		}
		return new SuccessResult();
	}
	
	
	
	
	
	
	
	

}
