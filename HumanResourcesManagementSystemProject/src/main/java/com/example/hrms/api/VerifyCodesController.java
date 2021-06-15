package com.example.hrms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.VerifyCodeService;
import com.example.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/verifyCodes")
public class VerifyCodesController {
	private VerifyCodeService verifyCodeService;
	
	@Autowired
	public VerifyCodesController(VerifyCodeService verifyCodeService) {
		super();
		this.verifyCodeService = verifyCodeService;
	}
	
	@PutMapping("/{verifyCode}")
	public Result update(@PathVariable("verifyCode") String verifyCode) {
		return this.verifyCodeService.verifyUser(verifyCode);
	}
	
}
