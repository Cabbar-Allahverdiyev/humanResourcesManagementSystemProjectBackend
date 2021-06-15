package com.example.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.business.abstracts.JobPostingService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.JobPosting;

@CrossOrigin
@RestController
@RequestMapping("/api/jobposting")
public class JobPostingController {
	private JobPostingService jobPostingService;
	
	@Autowired
	public JobPostingController(JobPostingService jobPostingService) {
		super();
		this.jobPostingService = jobPostingService;
	}
	
	@GetMapping("/getall")
	DataResult<List<JobPosting>> getAll(){
		return this.jobPostingService.getAll();
	}
	
	@GetMapping("/getByActivJobsAdverts")
	DataResult<List<JobPosting>> getByActivJobsAdverts(){
		return this.jobPostingService.getByActivJobsAdverts();
	}
	
	@GetMapping("/getByActivJobsAndByStartApplicationDate")
	public DataResult<List<JobPosting>> getByActivJobsAndByStartApplicationDate(){
		return this.jobPostingService.getByActiveTrueOrderByStartApplicationDate();
	}
	
	@GetMapping("/getByIsActiveTrueAndEmloyerId")
	DataResult<List<JobPosting>> getByIsActiveTrueAndEmloyerId(int employerId){
		return this.jobPostingService.getByIsActiveTrueAndEmloyerId(employerId);

	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobPosting jobPosting) {
		
		return  this.jobPostingService.add(jobPosting);
	}
	
	

}
