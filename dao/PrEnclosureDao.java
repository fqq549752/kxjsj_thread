package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.PrEnclosure;

/**
 * 
* @ClassName: PrEnclosureDao 
* @Description: 项目附件表DAO 
* @author fuqq
* @date 2017年2月10日 下午4:39:20 
*
 */
public interface PrEnclosureDao {

	void insert(PrEnclosure prEnclosure);
	ArrayList<Map<String, Object>> queryAllByProjectId(String project_id);
	void deleteFake(PrEnclosure prEnclosure);
	ArrayList<PrEnclosure> queryByProjectIdAndType(PrEnclosure prEnclosure);
	void deleteEnclo(PrEnclosure prEnclosure);
	ArrayList<PrEnclosure> queryAll(String project_id);

}
