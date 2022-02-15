package com.polygogo.backend.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polygogo.backend.models.entity.User;
import com.polygogo.backend.models.service.IUserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginRestController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/{username}+{password}")
	public void login(@PathVariable String username, @PathVariable String password, HttpServletResponse response) {
		User user= userService.findById(username);
		if(user.getPassword().equals(password)) {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		}else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user, @PathVariable String id) {
		User actualUser = userService.findById(id);
		
		actualUser = user;
		
		return userService.save(actualUser);
	}
	
}
