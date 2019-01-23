package com.yr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.service.UserService;

@Component
public class TimedTask {
	@Autowired
	private UserService userService;
	
	@ResponseBody
    public void first() {
		userService.timerToNow(); // 定时任务不用调用,会自己执行
    } 
}
