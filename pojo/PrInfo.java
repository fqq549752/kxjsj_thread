package com.sino.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sino.common.BaseEntity;

/**
 * 
 * @ClassName: PrInfo
 * @Description: 项目基本信息实体类
 * @author fuqq
 * @date 2017年1月10日 下午4:40:48
 *
 */
public class PrInfo extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String project_id;
	private String project_num;
	private String version;
	private String name;
	private String dept;
	private String person;// 项目完成人
	private String charger;// 项目联系人
	private String charger_phone;
	private String charger_tel;
	private String charger_email;
	private String charger_address;
	private String theme;// 主题词
	private String plan_fund_name;// 具体计划及基金的名称和编号
	private String plan_fund_code;// 具体计划及基金的名称和编号
	private String sb_grade;// 申报等级
	private String tj_grade;// 推荐等级
	private Date sb_time;// 申报时间
	private String sb_type;// 申报类别
	private String economic_type;// 所属国民经济行业类别
	private String task;// 任务来源
	private String project_status;// 项目申请状态
	private String fallback;// 项目回退理由
	private String wp_group;// 网评组
	private String wp_group_name;// 网评组
	private String ys_group;// 会评组
	private String es_group;// 会评组
	private String result;// 评审结果
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date start_time;// 项目起始时间
	@DateTimeFormat(pattern = "yyyyMMdd")
	private Date end_time;// 项目完成时间
	private String sb_dept;// 申报单位
	private String tj_dept;// 推荐单位
	private int is_del;// 删除标识
	private String querySbTime;// 前台穿的是多值字符串
	private Date update_time;// 修改项目的时间
	private Date pdf_time;// 预览生成pdf的时间

	public String getYs_group() {
		return ys_group;
	}

	public void setYs_group(String ys_group) {
		this.ys_group = ys_group;
	}

	public String getEs_group() {
		return es_group;
	}

	public void setEs_group(String es_group) {
		this.es_group = es_group;
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

	public String getProject_num() {
		return project_num;
	}

	public void setProject_num(String project_num) {
		this.project_num = project_num;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public String getCharger_phone() {
		return charger_phone;
	}

	public void setCharger_phone(String charger_phone) {
		this.charger_phone = charger_phone;
	}

	public String getCharger_tel() {
		return charger_tel;
	}

	public void setCharger_tel(String charger_tel) {
		this.charger_tel = charger_tel;
	}

	public String getCharger_email() {
		return charger_email;
	}

	public void setCharger_email(String charger_email) {
		this.charger_email = charger_email;
	}

	public String getCharger_address() {
		return charger_address;
	}

	public void setCharger_address(String charger_address) {
		this.charger_address = charger_address;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSb_grade() {
		return sb_grade;
	}

	public void setSb_grade(String sb_grade) {
		this.sb_grade = sb_grade;
	}

	public String getTj_grade() {
		return tj_grade;
	}

	public void setTj_grade(String tj_grade) {
		this.tj_grade = tj_grade;
	}

	public Date getSb_time() {
		return sb_time;
	}

	public void setSb_time(Date sb_time) {
		this.sb_time = sb_time;
	}

	public String getSb_type() {
		return sb_type;
	}

	public void setSb_type(String sb_type) {
		this.sb_type = sb_type;
	}

	public String getEconomic_type() {
		return economic_type;
	}

	public void setEconomic_type(String economic_type) {
		this.economic_type = economic_type;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getProject_status() {
		return project_status;
	}

	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	public String getWp_group() {
		return wp_group;
	}

	public void setWp_group(String wp_group) {
		this.wp_group = wp_group;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public String getSb_dept() {
		return sb_dept;
	}

	public void setSb_dept(String sb_dept) {
		this.sb_dept = sb_dept;
	}

	public String getQuerySbTime() {
		return querySbTime;
	}

	public void setQuerySbTime(String querySbTime) {
		this.querySbTime = querySbTime;
	}

	public String getTj_dept() {
		return tj_dept;
	}

	public void setTj_dept(String tj_dept) {
		this.tj_dept = tj_dept;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getPlan_fund_name() {
		return plan_fund_name;
	}

	public void setPlan_fund_name(String plan_fund_name) {
		this.plan_fund_name = plan_fund_name;
	}

	public String getPlan_fund_code() {
		return plan_fund_code;
	}

	public void setPlan_fund_code(String plan_fund_code) {
		this.plan_fund_code = plan_fund_code;
	}

	public String getWp_group_name() {
		return wp_group_name;
	}

	public void setWp_group_name(String wp_group_name) {
		this.wp_group_name = wp_group_name;
	}

	public Date getPdf_time() {
		return pdf_time;
	}

	public void setPdf_time(Date pdf_time) {
		this.pdf_time = pdf_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
