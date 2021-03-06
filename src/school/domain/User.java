package school.domain;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.*;

/**
 * 用户domian
 * 
 * @author Ghost
 *
 */
// 使用注解@Table，标明对应的数据库表名为：house

@Table(name = "user")
public class User implements Serializable {

	@Id
	// 使用注解@Id标明属性id为该表的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 使用注解@GeneratedValue用于标注主键的生成策略，通过strategy属性指定
	// IDENTITY：采用数据库ID自增长的方式来自增主键字段
	private Integer id;

	private String name;

	private String password;

	private Date addtime;

	private String type;

	private String email;

	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}