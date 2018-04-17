package com.sino.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sino.dao.UserDao;
import com.sino.pojo.User;
import com.sino.service.UserService;
@Service
public class UserImpl implements UserService {
	@Autowired
	private UserDao dao;

	@Override
	public int findUser(User user) {
		return dao.queryUserIsExist(user);
	}

}
