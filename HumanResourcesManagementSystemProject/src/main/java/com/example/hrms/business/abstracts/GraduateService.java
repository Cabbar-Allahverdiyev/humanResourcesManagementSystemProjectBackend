package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.Graduate;

public interface GraduateService {
	Result add(Graduate graduate);
	DataResult<List<Graduate>> getAll();
}
