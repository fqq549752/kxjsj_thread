package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.OtherFile;

/**
 * 
* @ClassName: OtherFileDao 
* @Description: 项目其他情况附件DAO
* @author fuqq
* @date 2017年1月18日 下午4:52:19 
*
 */
public interface OtherFileDao {
	
	void insert(OtherFile otherFile);
	
	OtherFile queryByProjectIdAndType(OtherFile otherFile);
	
	void updateWordPath(OtherFile otherFile);
	
	ArrayList<Map<String, Object>> queryAll(String project_id);
	OtherFile queryByProjectId(String project_id);

	
	void updatePdfNameAndPath(Map<String, Object> otherFile);
	void deleteFake(OtherFile otherFile);
	void deleteOtherFile(OtherFile otherFile);
}
