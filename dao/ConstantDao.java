package com.sino.dao;

import java.util.ArrayList;

import com.sino.pojo.SysConstant;

public interface ConstantDao {
	ArrayList<SysConstant> queryConstantByType(SysConstant sysConstant);
}
