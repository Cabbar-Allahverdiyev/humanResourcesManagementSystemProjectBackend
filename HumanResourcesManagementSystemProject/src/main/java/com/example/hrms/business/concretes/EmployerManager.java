package com.example.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.business.rules.BusinessRulesMethods;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.EmployerDao;
import com.example.hrms.entities.concretes.Employer;

import  java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService{
	
	private EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.employerListed);
	}

	@Override
	public Result add(Employer employer) {
		
		Result rules=BusinessRules.run(
				BusinessRulesMethods.passwordRepeatCompatibilityWithPassword(employer.getPassword(), employer.getPasswordRepeat())
				//,BusinessRulesMethods.isTheEmailCorrect(employer.getEmail()),
				//BusinessRulesMethods.emailWithTheDomainCorrect(employer.getEmail(), employer.getWebsite())
				);
		
		if (rules!=null) {
			return rules;
		}
		
		this.employerDao.save(employer);
		return new SuccessResult(Messages.employerAdded);
	}
	
	
	//Business rules methods
	
	

}
