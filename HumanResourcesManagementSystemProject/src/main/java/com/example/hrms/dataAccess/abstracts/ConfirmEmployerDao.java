package com.example.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.entities.concretes.ConfirmEmployer;

public interface ConfirmEmployerDao extends JpaRepository<ConfirmEmployer, Integer>{
	ConfirmEmployer getByEmployer_Id(int id);
	boolean existsByEmployer_Id(int id);
}
