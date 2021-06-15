package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.User;

public interface VerifyCodeService {
	String createVerifyCode(User user);
	Result sendEmail(String mail);
	Result verifyUser(String code);
	
}
