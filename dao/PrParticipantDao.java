package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.Participant;

/**
 * 
* @ClassName: PrParticipantDao 
* @Description: 主要完成人表Dao 
* @author fuqq
* @date 2017年1月13日 下午1:08:11 
*
 */
public interface PrParticipantDao {
	void insert(Participant participant);
	ArrayList<Map<String, Object>> queryByProjectId(Participant participant);
	Participant  queryByProjectIdAndIdcard(Participant participant);
	void deletePart(Participant participant);
	void updateSort(Participant participant);
	void deleteFake(Participant participant);

}
