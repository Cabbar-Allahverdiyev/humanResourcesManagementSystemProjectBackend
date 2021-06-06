package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.GraduateService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.business.BusinessRules;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.GraduateDao;
import com.example.hrms.entities.concretes.Graduate;

@Service
public class GraduateManager implements GraduateService{
	
	private GraduateDao graduateDao;
	
	@Autowired
	public GraduateManager(GraduateDao graduateDao) {
		super();
		this.graduateDao = graduateDao;
	}

	@Override
	public Result add(Graduate graduate) {
		Result rules=BusinessRules.run(
				isThereGraduateDescription(graduate.getDescription())
				);
		
		if (rules!=null) {
			return rules;
		}
		
		this.graduateDao.save(graduate);
		return new SuccessResult(Messages.graduateAdded);
	}

	@Override
	public DataResult<List<Graduate>> getAll() {
		
		return new SuccessDataResult<List<Graduate>>(this.graduateDao.findAll(),Messages.allGraduateListed);
	}
	
	//Business Rules Methods
	
	private Result isThereGraduateDescription(String description) {
		if (this.graduateDao.existsByDescription(description)) {
			return new ErrorResult(Messages.graduateDescriptionNowAvailable);
		}
		return new SuccessResult();
	}
}
