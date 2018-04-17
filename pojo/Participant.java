package com.sino.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sino.common.BaseEntity;

/**
 * 
* @ClassName: Participant 
* @Description: 项目主要完成人实体类 
* @author fuqq
* @date 2017年1月13日 下午12:39:05 
*
 */
public class Participant extends BaseEntity {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -8882691293030496797L;

	private int id;
	private String project_id;//项目ID
	private String name;
	private String idcard;//身份证号
	private int sort;//排名
	private String birthday;//出生日月
	private String sex;
	private String nation;//名族
	private String unit; //工作单位
	private String duties;//职务
	private String title;//职称
	private String tel;//办公电话
	private String telphone;//移动电话
	private String major;//所学专业
	private String work;//现从事专业
	private String specialty;//专业专长
	private String address;//通讯地址
	private String degree;//最高学历
	private String email;//电子信箱
	private String school;//毕业院校
	@DateTimeFormat(pattern="yyyyMM")
	private Date graduation;//毕业时间;
	private String awards;//获奖情况
	@DateTimeFormat(pattern="yyyyMM")
	private Date star_time;//参加项目的起始
	@DateTimeFormat(pattern="yyyyMM")
	private Date end_time;//参加项目的结束
	private String contribution;//项目技术贡献
	private String operator;//操作人
	private Date operat_time;
	private Date times;//创建时间
	private int is_del;//删除标识
	private String salt;//用于idcard加密
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
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	public Date getGraduation() {
		return graduation;
	}
	public void setGraduation(Date graduation) {
		this.graduation = graduation;
	}
	public Date getStar_time() {
		return star_time;
	}
	public void setStar_time(Date star_time) {
		this.star_time = star_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getOperat_time() {
		return operat_time;
	}
	public void setOperat_time(Date operat_time) {
		this.operat_time = operat_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	

}
