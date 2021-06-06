package com.example.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.hrms.entities.concretes.JobCategory;

public interface JobCategoryDao extends JpaRepository<JobCategory, Integer> {
	
	List<JobCategory> findByCategoryName(String categoryName);
}
