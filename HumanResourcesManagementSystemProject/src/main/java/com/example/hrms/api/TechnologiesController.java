package com.example.hrms.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.TechnologyService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Technology;

@CrossOrigin
@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {
	private TechnologyService technologyService;

	public TechnologiesController(TechnologyService technologyService) {
		super();
		this.technologyService = technologyService;
	}

	@GetMapping("/getall")
	public DataResult<List<Technology>> getAll(){
		return this.technologyService.getAll();
	}
	
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody Technology technology) {
		return this.technologyService.add(technology);
	  }
}
