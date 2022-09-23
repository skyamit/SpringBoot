package com.ImplementAll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ImplementAll.dao.UserDao;
import com.ImplementAll.model.UserEntity;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public List<UserEntity> getAll() {
		return userDao.findAll();
	}

	@Override
	public Optional<UserEntity> getById(long id) {
		return userDao.findById(id);
	}

	@Override
	public boolean deleteById(long id) {
		userDao.deleteById(id);
		return true;
	}

	@Override
	public void save(UserEntity user) {
		userDao.save(user);
	}
	
}
