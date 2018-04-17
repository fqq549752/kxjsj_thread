package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.PrDecunitView;
/**
 * 
* @ClassName: PrDecunitViewDao 
* @Description: 申报意见表DAO
* @author fuqq
* @date 2017年2月10日 下午2:16:21 
*
 */
public interface PrDecunitViewDao {

	void insert(PrDecunitView prDecunitView);
	ArrayList<Map<String, Object>> queryByProjectId(Map<String,Object> map);
	void deleteFake(PrDecunitView prDecunitView);
	void deleteDec(PrDecunitView prDecunitView);
}
