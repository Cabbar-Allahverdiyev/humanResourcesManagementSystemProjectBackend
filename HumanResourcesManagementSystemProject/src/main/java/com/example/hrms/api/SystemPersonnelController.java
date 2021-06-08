package com.example.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.SystemPersonnelService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.entities.concretes.SystemPersonnel;

@CrossOrigin
@RestController
@RequestMapping("/api/systempersonnels")
public class SystemPersonnelController{
	private SystemPersonnelService systemPersonnelService;
	
	@Autowired
	public SystemPersonnelController(SystemPersonnelService systemPersonnelService) {
		super();
		this.systemPersonnelService = systemPersonnelService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<SystemPersonnel>> getAll(){
		return this.systemPersonnelService.getAll();
	}
}
