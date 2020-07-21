package com.pdt.moduleorder9002.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.pdt.moduleorder9002.model.User;
import com.pdt.moduleorder9002.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl implements UserService {

	@Override
	public User findByUsername(String username) {
		if(username.equals("admin")){
			User user = new User();
			user.setId(1L);
			user.setUsername(username);
			String password = new BCryptPasswordEncoder().encode("123");
			user.setPassword(password);
			return user;
		}else{
			return null;
		}
	}

	/**
	 * 获取角色数据权限
	 *
	 * @param user 用户信息
	 * @return 角色权限信息
	 */
	public Set<String> getRolePermission(User user) {
		Set<String> permissions = new HashSet<>();
		permissions.add("role:user-view");
		permissions.add("role:user-add");
		permissions.add("role:user-edit");
		permissions.add("role:user-delete");
		return permissions;
	}

	/**
	 * 获取菜单数据权限
	 *
	 * @param user 用户信息
	 * @return 菜单权限信息
	 */
	public Set<String> getMenuPermission(User user) {
        Set<String> permissions = new HashSet<>();
		permissions.add("menu:user");
		permissions.add("menu:order");
		return permissions;
	}
}
