package com.sino.dao;

import java.util.List;

import com.sino.pojo.PrWordProcedure;

public interface PrWordProceDureDao {

	public List<PrWordProcedure> findPrWord();
	public void updateStatus(PrWordProcedure prWordProcedure);
	public void updatePath(PrWordProcedure prWordProcedure);
	public List<PrWordProcedure> findInfoByProjectId(String project_id);
	public PrWordProcedure findById(int id);
}
