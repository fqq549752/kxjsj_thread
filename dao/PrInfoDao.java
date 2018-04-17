package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.PrInfo;

public interface PrInfoDao {
	void insert(PrInfo prInfo);
	void updateBaseInfo(PrInfo prInfo);
	ArrayList<PrInfo> query(Map<String,Object> map);
	Map<String,Object> queryByProjectId(PrInfo prInfo);
	void deleteFake(PrInfo prInfo);
	int queryProCount(Map<String,Object> map);
	PrInfo queryPrInfoByProjectId(PrInfo prInfo);

}
