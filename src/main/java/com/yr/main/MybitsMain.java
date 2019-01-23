package com.yr.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.yr.config.DBConfig1;
import com.yr.config.DBConfig2;

//@ComponentScan(basePackages = "com.yr") // 扫描所有注解  --- 声明式事务(只能回滚一个数据源)
//@MapperScan(basePackages = "com.yr.mapper") // 扫描Mapper接口类 , 如果不加, 也可以在每个mapper上添加@Mapper注解 ,并且这里还要多填一个注解(不知道是哪个注解)
//@EnableAutoConfiguration // 扫描配置文件
////@SpringBootApplication
//@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解

//@ComponentScan(basePackages = "com.yr") // 分布式事务 -- 可回滚多个数据源
//@EnableCaching // 开启缓存注解
//@EnableAutoConfiguration
//@EnableAsync
//@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class }) // 加载属性文件的实体类

@ComponentScan(basePackages = "com.yr")
@SpringBootApplication // 等价于@Configuration，@EnableAutoConfiguration和@ComponentScan
@MapperScan("com.yr.mapper")  //mybatis mapper搜索路径
@EnableCaching   //缓存启动
@EnableScheduling // 开启定时任务
//@EnableAsync //开启异步调用
@PropertySource("classpath:application-prd.properties")
@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class }) // 加载属性文件的实体类
public class MybitsMain {
	public static void main(String[] args) {
		SpringApplication.run(MybitsMain.class, args);
	}
}
