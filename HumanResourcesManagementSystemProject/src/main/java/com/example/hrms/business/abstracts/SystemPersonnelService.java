package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.entities.concretes.SystemPersonnel;

public interface SystemPersonnelService {
 DataResult<List<SystemPersonnel>> getAll();
}
