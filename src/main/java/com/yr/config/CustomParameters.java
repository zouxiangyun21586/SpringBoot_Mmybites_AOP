package com.yr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义参数 (在 application.properties 文件中自定义属性)
 * 
 * @author zxy-un
 * 
 *         2018年7月19日 下午7:58:55
 */
@Component
// @ConfigurationProperties(prefix = "com.yr") // 如果不想在每个属性上加上 @Value注释 , 使用这个注解也可以
public class CustomParameters {

	// 获取application.properties的属性
	@Value("${com.yr.name}")
	private String name;

	@Value("${com.yr.age}")
	private int age;

	@Value("${com.prod.testName}")
	private String testName;

	@Value("${com.prod.testAddr}")
	private String testAddr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestAddr() {
		return testAddr;
	}

	public void setTestAddr(String testAddr) {
		this.testAddr = testAddr;
	}

}