package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.Company;

/**
 * 
* @ClassName: PrCompanyDao 
* @Description: 主要完成单位表dao
* @author fuqq
* @date 2017年1月13日 下午2:11:11 
*
 */
public interface PrCompanyDao {

	void insert(Company company);
	ArrayList<Map<String, Object>> queryByProjectId(String project_id);
	Company queryByProjectIdAndName(Company company);
	void deleteComp(Company company);
	void updateSortInfo(Company company);
	void deleteFake(Company company);
}
