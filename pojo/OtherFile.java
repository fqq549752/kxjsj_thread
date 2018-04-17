package com.sino.pojo;

import java.util.Date;

import com.sino.common.BaseEntity;

public class OtherFile extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6739512342214202274L;

	private int id;
	private String project_id;//项目ID
	private String file_name;//文件名
	private String pdf_name;//pdf文件名
	private String pdf_path;//pdf文件存放路径
	private String word_path;//word文件存放路径
	private Date upload_time;//上传时间
	private int type;//上传类型
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getPdf_path() {
		return pdf_path;
	}
	public void setPdf_path(String pdf_path) {
		this.pdf_path = pdf_path;
	}
	public String getWord_path() {
		return word_path;
	}
	public void setWord_path(String word_path) {
		this.word_path = word_path;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
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
	public String getPdf_name() {
		return pdf_name;
	}
	public void setPdf_name(String pdf_name) {
		this.pdf_name = pdf_name;
	}
	
	
}
