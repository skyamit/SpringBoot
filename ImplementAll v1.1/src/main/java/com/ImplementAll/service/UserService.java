package com.ImplementAll.service;

import java.util.List;
import java.util.Optional;

import com.ImplementAll.model.UserEntity;

public interface UserService {
	public List<UserEntity> getAll();
	public Optional<UserEntity>  getById(long id);
	public boolean deleteById(long id);
	public void save(UserEntity user);
}
