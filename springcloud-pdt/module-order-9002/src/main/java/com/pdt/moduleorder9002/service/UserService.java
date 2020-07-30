package com.pdt.moduleorder9002.service;

import com.pdt.moduleorder9002.model.User;

import java.util.Set;

/**
 * 用户管理
 * @author Louis
 * @date Jun 29, 2019
 */
public interface UserService {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	Set<String> getMenuPermission(User user);

	Set<String> getRolePermission(User user);

}
