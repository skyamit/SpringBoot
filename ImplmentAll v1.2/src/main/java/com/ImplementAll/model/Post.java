package com.ImplementAll.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	int id;
	
	@Column
	(name="content")
	String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	UserEntity userEntity;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public UserEntity getUserEntity() {
		return userEntity;
	}
	
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(content, id, userEntity);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(content, other.content) && id == other.id && Objects.equals(userEntity, other.userEntity);
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", userEntity=" + userEntity + "]";
	}
	
	
}
