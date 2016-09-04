package com.at.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserStatus;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Rent;
import com.at.library.model.User;

@Service
public class UserServiceImplementation implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RentService rentService;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = transform(userDTO);
		user.setUserStatus(UserStatus.NORMAL);
		return transform(userDao.save(user));
	}

	@Override
	public void delete(Integer id) throws UserNotFoundException {
		User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException();
		user.setUserStatus(UserStatus.DELETED);
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
	public User findOne(Integer id) throws UserNotFoundException {
		final User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException();
		return user;
	}
	
	public User transform(UserDTO userDTO) {
		return dozer.map(userDTO, User.class);
	}
	
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public Boolean isPunished(User user) {
		return user.getUserStatus().equals(UserStatus.BANNED);
	}
	
	/**
	 * Batch process that punishes Users that didn't return the Book on time
	 */
	@Scheduled(cron = "0 0 2 1/1 * ?")
	private void punish() {
		log.debug("Batch process starting. Penalizing users.");
		List<Rent> rents = rentService.findPunishable();
		for(Rent rent : rents) {
			
			final DateTime todayDateTime = new DateTime();
			final DateTime returnDateTime = new DateTime(rent.getReturnDate());
			Days punishedDays = Days.daysBetween(todayDateTime, returnDateTime).multipliedBy(3);

			final User user = rent.getUser();
			
			if (user.getPunishDate() == null) {
				DateTime startPunishmentDateTime = todayDateTime;
				DateTime endPunishmentDateTime = startPunishmentDateTime.plus(punishedDays);
				user.setUserStatus(UserStatus.BANNED);
				user.setPunishDate(startPunishmentDateTime.toDate());
				user.setForgiveDate(endPunishmentDateTime.toDate());
				userDao.save(user);
				rentService.setAlreadyPunished(rent);
				log.debug("User %s was punished with %s days", user.getId(), punishedDays);
			}
		}
	}

	/**
	 * Batch process that forgives Users after a punishment interval
	 */
	@Scheduled(cron = "0 0 3 1/1 * ?")
	private void forgive() {
		log.debug("Batch process starting. Forgiving users.");
		List<User> users = userDao.findPunished();
		for(User user : users) {
			if (user.getForgiveDate().before(new Date())) {
				user.setPunishDate(null);
				user.setForgiveDate(null);
				user.setUserStatus(UserStatus.NORMAL);
				userDao.save(user);
				log.debug("User %s was forgiven", user.getId());
			}
		}
	}
		
}
