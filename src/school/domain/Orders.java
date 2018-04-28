package school.domain;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.*;

/**
 * 订单domain
 * 
 * @author Ghost
 *
 */
// 使用注解@Table，标明对应的数据库表名为：house
@Table(name = "orders")
public class Orders implements Serializable {

	@Id
	// 使用注解@Id标明属性id为该表的主键
	private Integer id;

	private Integer userid;

	private Integer houseid;

	private Date addtime;

	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
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

}