package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.SystemPersonnelService;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.dataAccess.abstracts.SystemPersonnelDao;
import com.example.hrms.entities.concretes.SystemPersonnel;

@Service
public class SystemPersonnelManager implements SystemPersonnelService{
	
	private SystemPersonnelDao systemPersonnelDao;
	
	@Autowired
	public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao) {
		super();
		this.systemPersonnelDao = systemPersonnelDao;
		
	}

	@Override
	public DataResult<List<SystemPersonnel>>  getAll() {
		return new SuccessDataResult<List<SystemPersonnel>>(this.systemPersonnelDao.findAll()) ;
	}
}
