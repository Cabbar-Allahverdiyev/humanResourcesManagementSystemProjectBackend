package com.example.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.business.constants.Messages;
import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.Result;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.core.utilities.results.SuccessResult;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService {
	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<User> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DataResult<User> getOne(int id) {
		return new SuccessDataResult<User>( this.userDao.getOne(id));
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult(Messages.userAdded);
	}

	@Override
	public Result update(User user, int id) {
		User updateUser=this.userDao.findById(id);
		if (updateUser.equals(null)) {
			return new ErrorResult(Messages.invalidUserUpdate);
		}
		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());
		updateUser.setVerify(true);  //*REFACTOR et update edildikde avtomatik tesdiqlenir
 		this.userDao.save(updateUser);
		return new SuccessResult(Messages.userUpdated);
	}
	
}
