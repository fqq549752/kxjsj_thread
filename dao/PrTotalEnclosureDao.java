package com.sino.dao;

import com.sino.pojo.TotalEnclosure;

public interface PrTotalEnclosureDao {

	void insert(TotalEnclosure totalEnclosure);
	void deleteFake(TotalEnclosure totalEnclosure);
	TotalEnclosure queryAllByProjectId(String project_id);
	void deleteTotalEnclosure (String project_id);
}
