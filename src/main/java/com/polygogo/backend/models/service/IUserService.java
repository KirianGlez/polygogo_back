package com.polygogo.backend.models.service;

import java.util.List;

import com.polygogo.backend.models.entity.User;


public interface IUserService {
	
	public User findById(String username);
	
	public User save(User user);
	
}
