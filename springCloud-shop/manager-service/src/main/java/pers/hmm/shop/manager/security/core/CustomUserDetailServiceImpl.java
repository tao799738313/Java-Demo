package pers.hmm.shop.manager.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.hmm.shop.manager.entity.Role;
import pers.hmm.shop.manager.entity.UserInfo;
import pers.hmm.shop.manager.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义类 从数据库中查询用户信息
 */
@Service("customUserDetailService")
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 表单登录 自定义从数据库中查询用户、角色
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userService.findByUsername(username);

        //存储用户角色
        List<GrantedAuthority> list = new ArrayList<>();
        List<Role> roles = userInfo.getRoles();
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }
        }
        //AuthorityUtils.createAuthorityList() 留此工具类备注
        return new User(userInfo.getUsername(), userInfo.getPassword(), list);
    }
}
