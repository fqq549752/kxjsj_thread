package com.sino.dao;

import java.util.Map;

/**
 * 
* @ClassName: SequenceDao 
* @Description: 
* @author fuqq
* @date 2017年1月20日 上午11:34:40 
*
 */
public interface SequenceDao {
	
	/**
	 * 
	* @Title: currval 
	* @Description: 获取当前值
	* @param @param map
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int currval(Map<String, Object> map);
	/**
	 * 
	* @Title: nextval 
	* @Description: 获取下一个值
	* @param @param map
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int nextval(Map<String, Object> map);
	/**
	 * 
	* @Title: setval 
	* @Description: 设置默认值
	* @param @param map
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int setval(Map<String, Object> map);
	
	/**
	 * 
	* @Title: insertSeq 
	* @Description: 新增SEQ
	* @param @param map    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void insertSeq(Map<String, Object> map);
	/**
	 * 
	* @Title: delSeq 
	* @Description: 删除seq
	* @param @param map    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void delSeq(Map<String, Object> map);
	
	String queryOldSeq(String pr_type);
}
