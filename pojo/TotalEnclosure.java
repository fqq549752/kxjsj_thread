package com.sino.pojo;

import java.util.Date;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: TotalEnclosure 
* @Description: 总附件实体类
* @author fuqq
* @date 2017年3月7日 上午11:39:16 
*
 */
public class TotalEnclosure extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String project_id;//项目ID
	private String word_path;//word存储地址
	private String pdf_path;//学会版pdf存储地址
	private String ex_pdf_path;//专家版pdf存储地址
	private String zip_path;//亚索包地址
	private Date times;//存储时间
	private String operator;//操作人
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
	public String getWord_path() {
		return word_path;
	}
	public void setWord_path(String word_path) {
		this.word_path = word_path;
	}
	public String getPdf_path() {
		return pdf_path;
	}
	public void setPdf_path(String pdf_path) {
		this.pdf_path = pdf_path;
	}
	public String getEx_pdf_path() {
		return ex_pdf_path;
	}
	public void setEx_pdf_path(String ex_pdf_path) {
		this.ex_pdf_path = ex_pdf_path;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public String getZip_path() {
		return zip_path;
	}
	public void setZip_path(String zip_path) {
		this.zip_path = zip_path;
	}
	
}
