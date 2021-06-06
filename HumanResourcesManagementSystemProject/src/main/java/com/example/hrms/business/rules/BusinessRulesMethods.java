package com.example.hrms.business.rules;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.entities.abstracts.IEntity;

public class BusinessRulesMethods {

	public static Result passwordRepeatCompatibilityWithPassword(String password,String passwordRepeat) {
		if (!password.equals(passwordRepeat)) {
			return new ErrorResult(Messages.passwordAndPasswordRepeatInvalid);
		}
		return new SuccessResult();
	}
	
	public static Result isTheEmailCorrect(String email) {
		String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(email);
	     if (!matcher.matches()) {
			return new ErrorResult(Messages.emailInvalid);
		}
	    
	     return new SuccessResult();
	}
	
	 
	public static Result emailWithTheDomainCorrect(String email,String webAdres) {
		
		 if(!email.contains(webAdres)) {
		    	return new  ErrorResult(Messages.webDomainEqulasEmail); 
		     }
		 return new SuccessResult();
	}
	
//	public static Result isEmailNowAvailable(List<IEntity> result) {
//		if (result.stream().count()!=0) {
//			return new ErrorResult(Messages.thisEmailIsAvailable);
//		}
//		return new SuccessResult();
//	}
}
