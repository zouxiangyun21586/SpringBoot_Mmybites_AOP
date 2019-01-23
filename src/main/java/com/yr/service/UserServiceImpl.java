package com.yr.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yr.entity.User;
import com.yr.mapper.UserMapper;
import com.yr.mapper2.UserMapper2;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserMapper2 userMapper2;
	
	// value属性表示使用哪个缓存策略 (cache Name)
	public static final String DEMO_CACHE_NAME = "baseCache";
	 //这里的单引号不能少，否则会报错，被识别是一个对象;
	public static final String CACHE_KEY = "'user'"; // 我把它看作是引用实体类
	
//	@CachePut也可以标注在类上和方法上。使用@CachePut时我们可以指定的属性跟@Cacheable是一样的
//	@CachePut(value = DEMO_CACHE_NAME,key = "'user_'+#updated.getId()") 修改数据.
//	@CacheEvict(value = DEMO_CACHE_NAME,key = "'user_'+#updated.getId()") // 这是修改数据并且清除缓存
//	@CacheEvict(value = DEMO_CACHE_NAME,key = "'user_'+#id") // 这是删除数据并且清除缓存.
	@CacheEvict(value=DEMO_CACHE_NAME,key=CACHE_KEY)
	@Override
	public List<User> selMybits() {
		List<User> listUser = userMapper.sel();
		return listUser;
	}

	/**
	 * 定时任务
	 * 
	 * 秒、分、时、日、月、年
	 * 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
	 * 0 0 12 * * ? 每天中午12点触发
	 * 0 0/5 0 * * ? 每5分钟执行一次
	 */
	@Scheduled(cron = "0 0/1 * * * ?") // 每过 1 分钟启动
	public void timerToNow(){
		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	/**
	 * 同步调用执行结果: (注释 @EnableAsync 和 @Async 两个注解) 
	 * 	####UserController####   1
	 * 	####UserServiceImpl####  2
	 * 	####UserServiceImpl####  3
	 * 	####UserController####   4
	 * 
	 * 异步调用执行结果: (解开 @EnableAsync 和  @Async 两个注解)
	 * 	####UserController####   1
	 * 	####UserController####   4
	 * 	####UserServiceImpl####  2
	 * 	####UserServiceImpl####  3
	 * 
	 */
//	@Async
    public void async(){
        System.out.println("####UserServiceImpl####  2");
        IntStream.range(0, 5).forEach(d -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("####UserServiceImpl####  3");
    }
	
	/**
	 * PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
	 * PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
	 * PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
	 * PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
	 * PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
	 * PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
	 * PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。
	 */
	//如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值 , 表示使用底层数据库的默认隔离级别 ,事务超时时间设置 ,导致事务回滚的异常类数组(手动回滚--一般的异常也能触发事务回滚)
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	@Override
	public boolean ins(User user) { // 事务时不能捕获异常,捕获异常就不能回滚了  -- 声明式事务
		userMapper.ins(user);
//		int a = 1/0;
		userMapper.ins2(user);
		return true;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean ins2(User user) { // 事务时不能捕获异常,捕获异常就不能回滚了 -- 分布式事务
		userMapper.ins(user);
//		int a = 1/0;
		userMapper2.ins2(user);
		return true;
	}
	
	@Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;
    
    /**
     * 根据指定key 获取 String(内容)
     * @param key
     * @return
     */
    public String getStr(String key){
        return valOpsStr.get(key);
    }
    
    /**
     * 设置 Str 缓存 (设值)
     * @param key
     * @param val
     */
    public void setStr(String key, String val){
        valOpsStr.set(key,val);
    }
    
    /**
     * 删除指定key
     * @param key
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }

    
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;
    
    /**
     * 根据指定o获取Object
     * @param o
     * @return
     */
    public Object getObj(Object o){
        return valOpsObj.get(o);
    }

    /**
     * 设置obj缓存
     * @param o1
     * @param o2
     */
    public void setObj(Object o1, Object o2){
        valOpsObj.set(o1, o2);
    }

    /**
     * 删除Obj缓存
     * @param o
     */
    public void delObj(Object o){
        redisTemplate.delete(o);
    }
	
}