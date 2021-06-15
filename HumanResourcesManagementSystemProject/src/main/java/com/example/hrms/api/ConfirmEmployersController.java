package com.example.hrms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.ConfirmEmployerService;
import com.example.hrms.core.utilities.results.Result;
@RestController
@RequestMapping("/confirmEmployers")
public class ConfirmEmployersController {
	private ConfirmEmployerService confirmEmployerService;
	
	@Autowired
	public ConfirmEmployersController(ConfirmEmployerService confirmEmployerService) {
		super();
		this.confirmEmployerService = confirmEmployerService;
	}
	
	@PutMapping("/{companyName}")
	public Result update (@PathVariable("companyName") String companyName) {
		return this.confirmEmployerService.confirmUser(companyName);
	}
}	
