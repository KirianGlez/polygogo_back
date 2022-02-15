package com.polygogo.backend.models.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polygogo.backend.models.dao.IUserDao;
import com.polygogo.backend.models.entity.User;
import com.polygogo.backend.models.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}
	
	
}
