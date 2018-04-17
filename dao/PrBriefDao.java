package com.sino.dao;

import com.sino.pojo.PrBrief;

public interface PrBriefDao {

	void insert(PrBrief prBrief);
	
	void update(PrBrief prBrief);
	
	int queryCount(PrBrief prBrief);
}
