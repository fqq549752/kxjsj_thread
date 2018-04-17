package com.sino.pojo;

import java.util.Date;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: PrEnclosure 
* @Description:项目附件表实体类
* @author fuqq
* @date 2017年2月10日 下午4:35:28 
*
 */
public class PrEnclosure extends BaseEntity{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String project_id;
	private String file_name;//PDF附件名
	private int file_type;//附件类型
	private String file_path;//PDF文件路径
	private String snapshot_path;//PDF缩略图路径
	private Date times;//上传时间
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getSnapshot_path() {
		return snapshot_path;
	}
	public void setSnapshot_path(String snapshot_path) {
		this.snapshot_path = snapshot_path;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	public int getFile_type() {
		return file_type;
	}
	public void setFile_type(int file_type) {
		this.file_type = file_type;
	}

	
}
