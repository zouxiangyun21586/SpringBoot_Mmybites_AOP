package com.yr.service;

import java.util.List;

import com.yr.entity.User;

public interface UserService {
	public List<User> selMybits(); // 查询, 缓存
	public void async(); // 异步调用
	public void timerToNow(); // 定时任务
	public boolean ins(User user); // 插入
	public boolean ins2(User user); // 插入
	
	/* StringRedisTemplate */
	public String getStr(String key); // 获取值
	public void setStr(String key, String val); // 设值
	public void del(String key); // 删值
	
	/* RedisTemplate */
	public Object getObj(Object o);
	public void setObj(Object o1, Object o2);
	public void delObj(Object o);
}
