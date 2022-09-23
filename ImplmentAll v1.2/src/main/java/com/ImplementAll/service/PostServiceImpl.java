package com.ImplementAll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ImplementAll.dao.PostDao;
import com.ImplementAll.model.Post;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostDao postDao;
	
	@Override
	public List<Post> getAll() {
		return postDao.findAll();
	}

	@Override
	public Optional<Post> getById(int id) {
		return postDao.findById(id);
	}

	@Override
	public boolean deleteById(int id) {
		postDao.deleteById(id);
		return true;
	}

	@Override
	public void save(Post post) {
		postDao.save(post);
	}
	
	public List<Post> getPostByUserId(long id){
		return postDao.getPostOfUser(id);
	}
}
