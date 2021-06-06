package com.example.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrms.entities.concretes.EmailVerification;

public interface EmailVerificationDao extends JpaRepository<EmailVerification, Integer> {
	List<EmailVerification> findByUserId(int userId);
}
