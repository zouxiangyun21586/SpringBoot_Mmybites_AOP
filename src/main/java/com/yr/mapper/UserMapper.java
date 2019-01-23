package com.yr.mapper;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.yr.entity.User;

@CacheConfig(cacheNames = "baseCache")
public interface UserMapper {
	@Cacheable
	public List<User> sel();
	public void ins(User user); // 插入
	public void ins2(User user); // 插入
}