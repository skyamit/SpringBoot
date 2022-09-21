package com.ImplementAll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ImplementAll.model.UserEntity;

public interface UserDao extends JpaRepository<UserEntity,Long>{

}
