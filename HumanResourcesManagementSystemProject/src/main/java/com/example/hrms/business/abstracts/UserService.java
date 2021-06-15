package com.example.hrms.business.abstracts;

import java.util.List;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getByEmail(String email);
	DataResult<User> getById(int id);
	DataResult<User> getOne(int id);
	Result add(User user);
	Result update(User user,int id);
}
