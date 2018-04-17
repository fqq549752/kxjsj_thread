package com.sino.pojo;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: PrDecunitView 
* @Description: 申报意见实体类
* @author fuqq
* @date 2017年2月10日 下午2:15:46 
*
 */
public class PrDecunitView extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 7268269434233657086L;
	
	
	private int id;
	private String project_id;
	private int type;//0申报意见，1推荐意见
	private String content;//意见
	private int is_del;//删除标识
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

}
