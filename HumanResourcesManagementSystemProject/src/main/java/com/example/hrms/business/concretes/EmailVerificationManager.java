package com.example.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.EmailVerificationService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.GenerateCode.GenerateRandomCode;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorDataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.EmailVerificationDao;
import com.example.hrms.entities.concretes.EmailVerification;

@Service
public class EmailVerificationManager implements EmailVerificationService{
	private EmailVerificationDao emailVerificationDao;
	
    @Autowired
	public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
		super();
		this.emailVerificationDao = emailVerificationDao;
	}

	@Override
	public Result generateCode(EmailVerification code, Integer id) {
		
		code.setCode(null);
		code.setVerified(false);
		if(code.isVerified() == false) {
			GenerateRandomCode generator = new GenerateRandomCode();
			String code_create = generator.create();
			code.setCode(code_create);
			code.setUserId(id);
	
			emailVerificationDao.save(code);
			
		}
		return new  SuccessResult(Messages.generateCodTosUserId);
		
	}

	@Override
	public DataResult<EmailVerification> verify(String verificationCode, Integer id) {
		EmailVerification ref = emailVerificationDao.findByUserId(id).stream().findFirst().get();
		if(ref.getCode().equals(verificationCode) && ref.isVerified() != true) {
			ref.setVerified(true);
			return  new SuccessDataResult<EmailVerification>(this.emailVerificationDao.save(ref),"BaЕџarД±lД±");
		}
		else if(ref.isVerified() == true) {
			return  new ErrorDataResult<EmailVerification>(null,"Zaten DoДџrulanmД±Еџ Hesap");
		}
		return  new ErrorDataResult<EmailVerification>(null,"DoДџrulama Kodu GeГ§ersiz");
	}
	
}
