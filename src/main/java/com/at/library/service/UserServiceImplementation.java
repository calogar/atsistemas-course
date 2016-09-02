package com.at.library.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserStatus;
import com.at.library.exceptions.UserNotFoundException;
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
	public void delete(Integer id) throws UserNotFoundException {
		User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException();
		user.setUserStatus(UserStatus.SUSPENDED);
		userDao.save(user);
	}

	@Override
	public List<UserDTO> search(String name, String dni, Pageable pageable) {
		List<User> users = userDao.search(name, dni, pageable);
		List<UserDTO> userDTOs = new ArrayList();
		for(User user : users) {
			userDTOs.add(transform(user));
		}
		return userDTOs;
	}
	
	@Override
	public UserDTO findOne(Integer id) throws UserNotFoundException {
		final User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException();
		return transform(user);
	}
	
	public User transform(UserDTO userDTO) {
		return dozer.map(userDTO, User.class);
	}
	
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}
	
}
