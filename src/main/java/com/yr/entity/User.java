package com.yr.entity;

import java.io.Serializable;

/**
 * 用户对象
 * @author zxy-un
 * 
 * 2018年7月13日 上午9:25:14
 */
public class User  implements Serializable{ // 因缓存需要 所以序列化
	private int id;
	private String name;
	private int age;
	private String sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

}
