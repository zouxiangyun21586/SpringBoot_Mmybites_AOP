package com.yr.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.config.CustomParameters;
import com.yr.entity.User;
import com.yr.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private CustomParameters customParameters; // 自定义参数
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/custom")
	@ResponseBody
    public String custom() {
        System.out.println("=================================== Name: "+customParameters.getName() + "  Age: " + customParameters.getAge());
        System.out.println("*********************************** testName: "+customParameters.getTestName() + " testAge: " +customParameters.getTestAddr());
        return "custom";
    }
	
	@RequestMapping("/first")
	@ResponseBody
    public Object first() {
		logger.info("first方法===");
		userService.timerToNow(); // 定时任务不用调用,会自己执行
        return "first controller";  
    } 
	
	/**
	 * 异步调用
	 * @author zxy-un
	 * 
	 * @return
	 * 
	 * 下午7:42:54
	 */
	@RequestMapping("/async")
	@ResponseBody
    public String async(){
        System.out.println("####UserController####   1");
        userService.async();
        System.out.println("####UserController####   4");
        return "success";
    }
  
    @RequestMapping("/doError")  
    @ResponseBody
    public Object error() {
    	logger.error("被除数不能为0");
        return 1 / 0;  
    }  
	
	@RequestMapping("/mybitsTest")
    public String selMybits(Map<String, Object> map) {
		List<User> listUser = userService.selMybits();
		map.put("list", listUser);
		return "mybits";
    }
	
	@RequestMapping("/mybitsIns")
	public String insMybits(Map<String, Object> map,User user) {
		user.setId(11);
		user.setName("测试声明式事务");
		user.setAge(111);
		user.setSex("未知");
		boolean aa = userService.ins(user);
		if(aa){
			map.put("over", "succss");
			logger.info("succss=============================");
			return "inster";
		} else {
			map.put("over", "error");
			logger.error("error--------------------------");
			return "inster";
		}
	}
	
	@RequestMapping("/mybitsIns2")
	public String insMybits2(Map<String, Object> map,User user) {
		user.setId(22);
		user.setName("测试分布式事务");
		user.setAge(222);
		user.setSex("男");
		boolean aa = userService.ins2(user);
		if(aa){
			map.put("over", "succss -- 成功!!!");
			logger.info("succss=============================");
			return "inster";
		} else {
			map.put("over", "error -- 失败!!!");
			logger.error("error-----------------------------");
			return "inster";
		}
	}
	
	
	
}
