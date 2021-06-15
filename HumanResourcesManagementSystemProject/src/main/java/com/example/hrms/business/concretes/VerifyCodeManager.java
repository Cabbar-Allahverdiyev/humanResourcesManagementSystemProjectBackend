package com.example.hrms.business.concretes;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.business.abstracts.VerifyCodeService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.VerifyCodeDao;
import com.example.hrms.entities.concretes.User;
import com.example.hrms.entities.concretes.VerifyCode;

@Service
public class VerifyCodeManager implements VerifyCodeService {
	private VerifyCodeDao verifyCodeDao;
	private UserService userService;
	private VerifyCode verifyCode;

	@Autowired
	public VerifyCodeManager(VerifyCodeDao verifyCodeDao,UserService userService) {
		super();
		this.verifyCodeDao = verifyCodeDao;

	}

	@Override
	public String createVerifyCode(User user) {
		String randomVerifyCode = UUID.randomUUID().toString();
		LocalDate nowDate = LocalDate.now();
		// VerifyCode
		// newVerifyCode=this.verifyCodeDao.getOne(this.verifyCode.getUserId().getId());
		VerifyCode newVerifyCode = new VerifyCode();
		newVerifyCode.setUserId(user);
		newVerifyCode.setConfirmedDate(nowDate);
		newVerifyCode.setVerifyCode(UUID.randomUUID().toString());
		this.verifyCodeDao.save(newVerifyCode);

		return randomVerifyCode;
	}

	@Override
	public Result sendEmail(String mail) {

		return new SuccessResult("Təsdiqləmə kodu poçt ünvanınıza göndərildi/*fake servis*"+mail);
	}

	@Override
	public Result verifyUser(String code) {

		if (!this.verifyCodeDao.existsByVerifyCode(code)) {
			return new ErrorResult(Messages.incorrectVerificationProcess);
		}

		if (this.verifyCodeDao.getOne(this.verifyCode.getId()).isConfirmed()) {
			return new ErrorResult(Messages.previouslyApprovedCompany);
		}

		LocalDate nowDate = (LocalDate.now());
		VerifyCode newVerifyCode = new VerifyCode();
		newVerifyCode.setConfirmed(true);
		newVerifyCode.setConfirmedDate(nowDate);
		this.verifyCodeDao.save(newVerifyCode);

		User newUser = this.userService.getOne(this.verifyCode.getUserId().getId()).getData();
		newUser.setVerify(true);
		this.userService.update(newUser, newUser.getId());

		return new SuccessResult(Messages.verificationSuccessful);
	}
}
