package com.sino.pojo;

import java.util.Date;

public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String user_id;
	private String name;
	private String password;
	private String tjdw;
	private String disname;
	private String phone;//手機
	private String tel;//座機
	private String email;
	private String dept_name;
	private String status;
	private Date times;
	private String operator;
	private Date operat_time;
	private String is_del;	
	private String role_id;
	private String reject_reason;//拒绝理由
	private int user_type;//用户类型

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTjdw() {
		return tjdw;
	}
	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}
	public String getDisname() {
		return disname;
	}
	public void setDisname(String disname) {
		this.disname = disname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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

	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public Date getOperat_time() {
		return operat_time;
	}
	public void setOperat_time(Date operat_time) {
		this.operat_time = operat_time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_id=" + user_id + ", name=" + name + ", password=" + password + ", tjdw="
				+ tjdw + ", disname=" + disname + ", phone=" + phone + ", tel=" + tel + ", email=" + email
				+ ", dept_name=" + dept_name + ", status=" + status + ", times=" + times + ", operator=" + operator
				+ ", operat_time=" + operat_time + ", is_del=" + is_del + ", role_id=" + role_id + ", reject_reason="
				+ reject_reason + ", user_type=" + user_type + "]";
	}


}
