package school.domain;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.*;

/**
 * 评论domain 包含属性（id，houseid，userid，addtime，content）
 * 
 * @author Ghost
 *
 */
// 使用注解@Table，标明对应的数据库表名为：comment
@Table(name = "comment")
public class Comment implements Serializable {

	@Id
	// 使用注解@Id标明属性id为该表的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 使用注解@GeneratedValue用于标注主键的生成策略，通过strategy属性指定
	// IDENTITY：采用数据库ID自增长的方式来自增主键字段
	private Integer id;

	private Integer houseid;

	private Integer userid;

	private Date addtime;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHouseid() {
		return houseid;
	}

	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}