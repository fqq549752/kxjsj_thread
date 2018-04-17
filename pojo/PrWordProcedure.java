package com.sino.pojo;

import java.util.Date;

public class PrWordProcedure {
	private int id;
	private String project_id;
	private int type;
	private int pr_status;
	private int status;
	private String word_path;
	private String pdf_path;
	private String ex_word_path;
	private String ex_pdf_path;
	private int is_flag;
	private String error_log;
	private Date insert_time;
	private Date operate_time;
	private int file_status;
	private long trans_time;
	private String file_length;

	public long getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(long trans_time) {
		this.trans_time = trans_time;
	}

	public String getFile_length() {
		return file_length;
	}

	public void setFile_length(String file_length) {
		this.file_length = file_length;
	}

	public String getEx_word_path() {
		return ex_word_path;
	}

	public void setEx_word_path(String ex_word_path) {
		this.ex_word_path = ex_word_path;
	}

	public String getEx_pdf_path() {
		return ex_pdf_path;
	}

	public void setEx_pdf_path(String ex_pdf_path) {
		this.ex_pdf_path = ex_pdf_path;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public Date getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getIs_flag() {
		return is_flag;
	}

	public void setIs_flag(int is_flag) {
		this.is_flag = is_flag;
	}

	@Override
	public String toString() {
		return "PrWordProcedure [id=" + id + ", project_id=" + project_id + ", type=" + type + ", status=" + status
				+ ", word_path=" + word_path + ", pdf_path=" + pdf_path + ", is_flag=" + is_flag + ", errr_log="
				+ error_log + ", insert_time=" + insert_time + ", operate_time=" + operate_time + "]";
	}

	public String getError_log() {
		return error_log;
	}

	public void setError_log(String error_log) {
		this.error_log = error_log;
	}

	public int getPr_status() {
		return pr_status;
	}

	public void setPr_status(int pr_status) {
		this.pr_status = pr_status;
	}

	public int getFile_status() {
		return file_status;
	}

	public void setFile_status(int file_status) {
		this.file_status = file_status;
	}

}
