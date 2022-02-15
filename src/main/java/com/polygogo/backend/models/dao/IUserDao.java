package com.polygogo.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.polygogo.backend.models.entity.User;

public interface IUserDao extends CrudRepository<User, String>{
	
	
	
}
