package school.domain;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.*;

/**
 * 房屋domain
 * 
 * @author Ghost
 *
 */
// 使用注解@Table，标明对应的数据库表名为：house

@Table(name = "house")
public class House implements Serializable {

	@Id
	// 使用注解@Id标明属性id为该表的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 使用注解@GeneratedValue用于标注主键的生成策略，通过strategy属性指定
	// IDENTITY：采用数据库ID自增长的方式来自增主键字段
	private Integer id;

	private String title;

	private String type;

	private Date addtime;

	// 1 待审核 2 审核通过 3 审核不通过 4 已处理
	private String status;

	private Double money;

	private String content;

	private Integer userid;

	private String phone;

	private String address;

	private String car;

	private String fitup;
	// 楼层
	private String floor;

	private String style;

	private String estate;

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getFitup() {
		return fitup;
	}

	public void setFitup(String fitup) {
		this.fitup = fitup;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}