package com.sino.pojo;

import java.util.Date;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: Company 
* @Description:完成单位实体表 
* @author fuqq
* @date 2017年1月13日 下午1:45:00 
*
 */
public class Company extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -3793414654843049927L;
	
	private int id;
	private String project_id;//项目ID
	private int sort;//序号
	private String name;//单位名称
	private String ranking;//排名
	private String location;//所在地
	private String address;//通讯地址
	private String contact_person;//联系人
	private String contact_phone;//联系电话
	private String fax;//传真
	private String telphone;//移动电话
	private String postcode;//邮政编码
	private String email;//邮箱
	private String contribution;//主要贡献
	private String operator;//操作人
	private Date operat_time;//操作时间
	private Date times;//创建时间
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContribution() {
		return contribution;
	}
	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperat_time() {
		return operat_time;
	}
	public void setOperat_time(Date operat_time) {
		this.operat_time = operat_time;
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

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	

}
