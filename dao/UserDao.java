package com.sino.dao;

import java.util.ArrayList;
import java.util.Map;

import com.sino.pojo.User;

public interface UserDao {
	void addUser(User user);

	/**
	 * 
	 * @Title: queryUserIsExist @Description: 根据可变条件查询用户是否已存在 @param @param
	 * user @param @return 设定文件 @return int 返回类型 @throws
	 */
	int queryUserIsExist(User user);

	User queryUserByUserNameAndPass(User user);

	/**
	 * 
	 * @Title: queryUserHasApprovedByTjdw @Description:
	 * 查询已审批处理过的用户 @param @param user @param @return 设定文件 @return
	 * ArrayList<User> 返回类型 @throws
	 */
	ArrayList<User> queryUserHasApprovedByTjdw(Map<String, Object> user);

	/**
	 * 
	 * @Title: queryUserHasApprovedByTjdw @Description:
	 * 查询未审批处理过的用户 @param @param user @param @return 设定文件 @return
	 * ArrayList<User> 返回类型 @throws
	 */
	ArrayList<User> queryUserHasNotApprovedByTjdw(Map<String, Object> user);

	/**
	 * 
	 * @Title: queryUserByRoleId @Description: 根据用户类型查找用户 @param @param
	 * user @param @return 设定文件 @return ArrayList<User> 返回类型 @throws
	 */
	ArrayList<User> queryUserByUserTypeAndStatus(User user);

	/**
	 * 
	 * @Title: updateStatusByName @Description: 修改用户状态 @param @param user
	 * 设定文件 @return void 返回类型 @throws
	 */
	void updateStatusByName(User user);

	/**
	 * 
	 * @Title: updatePass @Description: TODO修改用户名密码 @param @param user
	 * 设定文件 @return void 返回类型 @throws
	 */
	void updatePass(User user);

	/**
	 * 
	 * @Title: deleteUser @Description: 批量删除用户 @param @param user 设定文件 @return
	 * void 返回类型 @throws
	 */
	void deleteUser(Map<String, Object> user);

	void updateUserInfo(User user);

	int queryUserHasApprovedCount(Map<String, Object> map);

	User queryUserAsExpert(User user);
	
	int queryUserInfoCount(Map<String, Object> map);
}
