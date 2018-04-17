package com.sino.dao;

import java.util.Map;

import com.sino.pojo.PrNotice;

/**
 * 
* @ClassName: PrNoticeDao 
* @Description: 告示公告DAO
* @author fuqq
* @date 2017年1月13日 下午6:03:57 
*
 */
public interface PrNoticeDao {

	void insert(PrNotice prNotice);
	
	Map<String, Object> query(String projectId);
	
	void deleteNotice(String project_id);
}
