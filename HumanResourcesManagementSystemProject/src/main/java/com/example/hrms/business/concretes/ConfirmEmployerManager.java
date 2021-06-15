package com.example.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.ConfirmEmployerService;
import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.ConfirmEmployerDao;
import com.example.hrms.entities.concretes.ConfirmEmployer;
import com.example.hrms.entities.concretes.Employer;

@Service
public class ConfirmEmployerManager implements ConfirmEmployerService {
	
	private ConfirmEmployerDao confirmEmployerDao;
	
	private EmployerService employerService;
	
	@Autowired
	public ConfirmEmployerManager(ConfirmEmployerDao confirmEmployerDao,EmployerService employerService) {
		super();
		this.confirmEmployerDao = confirmEmployerDao;
		this.employerService=employerService;
		
	}
	@Override
	public Result createConfirmEmployer(Employer employer) {
		ConfirmEmployer confirmEmployer=new ConfirmEmployer();
		confirmEmployer.setEmployer(employer);
		confirmEmployer.setStaffUser(1);
		
		this.confirmEmployerDao.save(confirmEmployer);
		return new SuccessResult();
	}
	@Override
	public Result confirmUser(String companyName) {
		Result rules=BusinessRules.run(
				existsByCompanyName(companyName),
				this.employerService.isUserConfirm(companyName)
				
				);
		
		if (rules!=null) {
			return rules;
		}
		
		Employer employer =new Employer();
		ConfirmEmployer confirmEmployer=new ConfirmEmployer();
		
		employer=this.employerService.getByCompanyName(companyName).getData();
		employer.setUserConfirm(true);
		int employerId=employer.getId();
		this.employerService.update(employer,employerId);
		
		confirmEmployer=this.confirmEmployerDao.getByEmployer_Id(employerId);
		confirmEmployer.setConfirmed(true);
		LocalDate nowDate=LocalDate.now();
		confirmEmployer.setConfirmedDate(nowDate);
		this.confirmEmployerDao.save(confirmEmployer);
		
		return new SuccessResult(Messages.verificationSuccessful);
	}
	
	//Business Rules 
	private Result existsByCompanyName(String companyName) {
		var result=this.employerService.getAllByCompanyName(companyName).getData();
		if (result.stream().count()==0) {
			return new ErrorResult(Messages.employersHaveNotCompanyNameInThisName);
		}
		return new SuccessResult();
	}
	
	
	
	
}