package com.neo.mapper;

import java.util.List;

import com.neo.model.User;

//这里只需要定义接口方法
public interface UserMapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}