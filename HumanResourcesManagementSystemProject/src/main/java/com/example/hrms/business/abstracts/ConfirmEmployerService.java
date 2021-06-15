package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Employer;

public interface ConfirmEmployerService {
	Result createConfirmEmployer( Employer employer);
	Result confirmUser(String companyName);
}
