package com.example.hrms.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.hrms.business.abstracts.ResumeService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Resume;
import com.example.hrms.entities.dtos.ResumeAddDto;
import com.example.hrms.entities.dtos.ResumeGetDto;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {
	
	private ResumeService resumeService;

	@Autowired
	public ResumesController(ResumeService resumeService) {
		super();
		this.resumeService = resumeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ResumeGetDto>> getAll(){
		return this.resumeService.getAll();
	}
	
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody ResumeAddDto resumeAddDto) {
		return this.resumeService.add(resumeAddDto);
	  }
	
	@PutMapping("/saveImage")
	public Result saveImage(@RequestBody MultipartFile file,@RequestParam int resumeId) {
		return this.resumeService.saveImage(file, resumeId);
		
	}
}
