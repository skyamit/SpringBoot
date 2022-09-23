package com.ImplementAll.service;

import java.util.List;
import java.util.Optional;

import com.ImplementAll.model.Post;

public interface PostService {
	public List<Post> getAll();
	public Optional<Post>  getById(int id);
	public boolean deleteById(int id);
	public void save(Post post);
}
