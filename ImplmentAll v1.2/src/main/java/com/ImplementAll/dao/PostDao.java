package com.ImplementAll.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ImplementAll.model.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

	@Query(value="select * from post where user_entity_id=:user_id",nativeQuery=true)
	public List<Post> getPostOfUser(long user_id);
	
}
