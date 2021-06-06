package com.example.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hrms.business.abstracts.ResumeService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.cloudinary.CloudinaryService;
import com.example.hrms.core.utilities.dtoConverter.DtoConverterService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.ResumeDao;
import com.example.hrms.entities.concretes.Resume;
import com.example.hrms.entities.dtos.ResumeAddDto;
import com.example.hrms.entities.dtos.ResumeGetDto;

@Service
public class ResumeManager implements ResumeService{
	
	private ResumeDao resumeDao;
	private CloudinaryService cloudinaryService;
	private DtoConverterService dtoConverterService;
	
	@Autowired
	public ResumeManager(ResumeDao resumeDao, CloudinaryService cloudinaryService, DtoConverterService dtoConverterService) {
		super();
		this.resumeDao = resumeDao;
		this.cloudinaryService = cloudinaryService;
		this.dtoConverterService = dtoConverterService;
	}

	@Override
	public Result add(ResumeAddDto resumeGetDto) {
		this.resumeDao.save((Resume) this.dtoConverterService.dtoClassConverter(resumeGetDto, Resume.class));
		return new SuccessResult(Messages.resumeAdded);
	}

	@Override
	public DataResult<List<ResumeGetDto>> getAll() {
		return new SuccessDataResult<List<ResumeGetDto>>(this.dtoConverterService.dtoConverter(this.resumeDao.findAll(), 
				ResumeGetDto.class),
				Messages.allResumesListed);
	}

	@Override
	public Result saveImage(MultipartFile file, int resumeId) {
		@SuppressWarnings("unchecked")
		Map<String,String> uploader=(Map<String,String>) this.cloudinaryService.save(file).getData();
		String imageUrl=uploader.get("url");
		Resume cv=this.resumeDao.getOne(resumeId);
		cv.setPhoto(imageUrl);
		this.resumeDao.save(cv);
		return new SuccessResult(Messages.saveImage);
	}

}
