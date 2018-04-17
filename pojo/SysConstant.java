package com.sino.pojo;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: SysConstant 
* @Description: 系统常量实体类 
* @author fuqq
* @date 2017年1月10日 下午2:37:35 
*
 */
public class SysConstant extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6206142479678690217L;
	
	private int id;
	private String code;
	private String name;
	private String type;
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
