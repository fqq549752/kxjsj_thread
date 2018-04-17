package com.sino.pojo;

import java.util.Date;

import com.sino.common.BaseEntity;


/**
 * 
* @ClassName: PrRate 
* @Description:评价简表实体类
* @author fuqq
* @date 2017年2月10日 下午4:00:01 
*
 */
public class PrRate extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String project_id;
	private String type;//所属得类别
	private String name;//类别项
	private String content;//内容
	private Date times;//分组时间
	private int is_del;//删除标识
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
	

}
