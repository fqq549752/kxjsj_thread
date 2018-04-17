package com.sino.dao;

import com.sino.pojo.Participant;

/**
 * 
* @ClassName: PrParticipantDao 
* @Description: 主要完成人表Dao 
* @author fuqq
* @date 2017年1月13日 下午1:08:11 
*
 */
public interface PrParticipantBaseDao {
	void insert(Participant participant);
	Participant queryByIdcard(String idcard);
}
