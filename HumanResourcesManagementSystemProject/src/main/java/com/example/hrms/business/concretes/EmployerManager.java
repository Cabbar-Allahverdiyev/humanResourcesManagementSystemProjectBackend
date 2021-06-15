package com.example.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.ConfirmEmployerService;
import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.business.abstracts.VerifyCodeService;
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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private UserService userService;
	private VerifyCodeService verifyCodeService;
	//private ConfirmEmployerService confirmEmployerService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, UserService userService, VerifyCodeService verifyCodeService
			//ConfirmEmployerService confirmEmployerService

	) {
		super();
		this.employerDao = employerDao;
		this.userService=userService;
		this.verifyCodeService=verifyCodeService;
		//this.confirmEmployerService=confirmEmployerService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.employerListed);
	}

	@Override
	public DataResult<List<Employer>> getAllByCompanyName(String companyName) {

		return new SuccessDataResult<List<Employer>>(this.employerDao.findAllByCompanyName(companyName),
				Messages.employersListedByCompanyName);
	}

	@Override
	public Result isUserConfirm(String companName) {
		if (this.employerDao.findByCompanyName(companName).isUserConfirm()) {
			return new ErrorResult(Messages.previouslyApprovedCompany);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<Employer> getByCompanyName(String companyName) {

		return new SuccessDataResult<Employer>(this.employerDao.findByCompanyName(companyName));
	}

	@Override
	public Result add(Employer employer) {
		
		Result rules=BusinessRules.run(
				BusinessRulesMethods.passwordRepeatCompatibilityWithPassword(employer.getPassword(), employer.getPasswordRepeat()),				
				isThereEmailCorrect(employer.getEmail()),
				BusinessRulesMethods.emailWithTheDomainCorrect(employer.getEmail(), employer.getWebsite())
				);
		
		if (rules!=null) {
			return rules;
		}
		
		this.employerDao.save(employer);
		this.verifyCodeService.createVerifyCode(this.userService.getOne(employer.getId()).getData());
		//this.confirmEmployerService.createConfirmEmployer(employer);
		this.verifyCodeService.sendEmail(employer.getEmail());
		return new SuccessResult(Messages.employerAdded);
	}

	@Override
	public Result update(Employer employer, int id) {
		Employer updateEmployer = this.employerDao.findById(id);
		if (updateEmployer.equals(null)) {
			return new ErrorResult(Messages.employerNotFound);
		}

		updateEmployer.setCompanyName(employer.getCompanyName());
		updateEmployer.setEmail(employer.getEmail());
		updateEmployer.setPassword(employer.getPassword());
		updateEmployer.setPhoneNumber(employer.getPhoneNumber());
		updateEmployer.setWebsite(employer.getWebsite());
		updateEmployer.setUserConfirm(true); // *REFACTOR et . update eildikde avtomatik tesdiqlenir
		this.employerDao.save(updateEmployer);
		return new SuccessResult(Messages.employerUpdate);
	}

	// Business rules methods

	private Result isThereEmailCorrect(String email) {
		List<Employer> result = this.employerDao.findByEmail(email);
		if (result.stream().count() != 0) {
			return new ErrorResult(Messages.emailInvalid);
		}
		return new SuccessResult();
	}

}
