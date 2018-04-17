package com.sino.pojo;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: PrNotice 
* @Description: 公示公告实体类
* @author fuqq
* @date 2017年1月13日 下午5:58:42 
*
 */
public class PrNotice extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1500566185075961182L;
	
	private int id;
	private String project_id;//项目ID
	private String pic_name;//公示公告名称
	private String pic_path;//公示公告路径
	private int is_del;
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
	public String getPic_name() {
		return pic_name;
	}
	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

}
