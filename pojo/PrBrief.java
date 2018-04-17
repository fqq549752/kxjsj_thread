package com.sino.pojo;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: PrBrief 
* @Description: 项目简介内容实体类
* @author fuqq
* @date 2017年2月10日 下午3:05:57 
*
 */
public class PrBrief extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -5626994855080917605L;
	
	private int id;
	private String project_id;
	private String brief;//项目简介
	private String background;//立项背景
	private String tech;//详细技术内容
	private String innovate;//主要技术创新点
	private String compare;//国内外比较
	private String application;//应用情况
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
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public String getInnovate() {
		return innovate;
	}
	public void setInnovate(String innovate) {
		this.innovate = innovate;
	}
	public String getCompare() {
		return compare;
	}
	public void setCompare(String compare) {
		this.compare = compare;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	
}
