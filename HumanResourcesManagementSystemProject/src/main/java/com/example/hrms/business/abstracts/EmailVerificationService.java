package com.example.hrms.business.abstracts;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.EmailVerification;

public interface EmailVerificationService {
	 Result generateCode(EmailVerification code,Integer id);
	 DataResult<EmailVerification> verify(String verificationCode,Integer id);
}
