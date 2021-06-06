package com.example.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.dtos.ResumeAddDto;
import com.example.hrms.entities.dtos.ResumeGetDto;

public interface ResumeService {
	Result add(ResumeAddDto resumeAddDto);
	DataResult<List<ResumeGetDto>> getAll();
	Result saveImage(MultipartFile file,int resumeId);
}
