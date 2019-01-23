package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yr.entity.User;
import com.yr.service.UserService;

/**
 * 运行测试
 * 1.StringRedisTemplate
 *   http://localhost:8080/setStr?key=testStr&val=testName
 *   http://localhost:8080/getStr?key=testStr
 *   http://localhost:8080/delStr?key=testStr
 * 2.RedisTemplate (可以存对象 -- 存入对象的时候注意对象中的属性 -- 插入没有的属性不会报错(不会显示多的属性值),少插入一个属性也不会报错(没插入的显示空))
 *   http://localhost:8080/setObj?key=testObj&id=1&name=zouxiangyun&age=18&sex=女
 *   http://localhost:8080/getObj?key=testObj
 *   http://localhost:8080/delObj?key=testObj
 * 
 * @author zxy-un
 * 
 * 2018年7月20日 下午5:40:42
 */
@RestController
public class RedisController {
	@Autowired
	private UserService userService;

    /**
     * 设置Str缓存
     * @param key
     * @param val
     * @return
     */
    @RequestMapping(value = "setStr")
    public String setStr(String key, String val){
        try {
            userService.setStr(key, val);
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 根据key查询Str缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "getStr")
    public String getStr(String key){
        return userService.getStr(key);
    }


    /**
     * 根据key产出Str缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "delStr")
    public String delStr(String key){
        try {
            userService.del(key);
            return "success";
        } catch (Exception e){
            return "error";
        }
    }


    /**
     * 设置obj缓存
     * @param key
     * @param user
     * @return
     */
    @RequestMapping(value = "setObj")
    public String setObj(String key, User user){ // User中有什么属性就放什么属性,不放也行
        try {
            userService.setObj(key, user);
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 获取obj缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "getObj")
    public Object getObj(String key){
        return userService.getObj(key);
    }


    /**
     * 删除obj缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "delObj")
    public Object delObj(String key){
        try {
            userService.delObj(key);
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
