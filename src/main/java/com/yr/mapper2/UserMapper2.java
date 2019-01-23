package com.yr.mapper2;

import java.util.List;

import com.yr.entity.User;

public interface UserMapper2 {
	public List<User> sel();
	public void ins(User user); // 插入
	public void ins2(User user); // 插入
}