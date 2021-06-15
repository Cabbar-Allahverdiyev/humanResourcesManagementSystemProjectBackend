package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Employer;

public interface EmployerService {
	DataResult<List<Employer>> getAll();
	Result add(Employer employer);
	DataResult<List<Employer>> getAllByCompanyName(String companyName);
	DataResult<Employer> getByCompanyName(String companyName);
	Result isUserConfirm(String companName);
	Result update (Employer employer,int id);
}
