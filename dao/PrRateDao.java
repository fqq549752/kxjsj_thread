package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.PrRate;

/**
 * 
* @ClassName: PrRateDao 
* @Description: 评价简表DAO
* @author fuqq
* @date 2017年2月10日 下午4:00:34 
*
 */
public interface PrRateDao {

	void insert(PrRate prRate);
	ArrayList<Map<String, Object>> queryByProjectId(PrRate prRate);
	void deleteFake(PrRate prRate);
	void deleteRate(PrRate prRate);
}
