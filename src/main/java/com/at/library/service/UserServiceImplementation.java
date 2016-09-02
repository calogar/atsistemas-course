package com.at.library.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserStatus;
import com.at.library.model.User;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = transform(userDTO);
		user.setUserStatus(UserStatus.ACTIVE);
		return transform(userDao.save(user));
	}

	@Override
	public void delete(Integer id) {
		// TODO: Check that the user exists and throw exception if not
		User user = userDao.findOne(id);
		user.setUserStatus(UserStatus.SUSPENDED);
		userDao.save(user);
	}

	@Override
	public List<UserDTO> search(String name, String dni) {
		List<User> users = userDao.search(name, dni);
		List<UserDTO> userDTOs = new ArrayList();
		for(User user : users) {
			userDTOs.add(transform(user));
		}
		return userDTOs;
	}
	
	@Override
	public UserDTO findOne(Integer id) {
		final User user = userDao.findOne(id);
		if(user == null)
			// TODO: throw an exception
		return transform(user);
	}
	
	public User transform(UserDTO userDTO) {
		return dozer.map(userDTO, User.class);
	}
	
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}
	
}
