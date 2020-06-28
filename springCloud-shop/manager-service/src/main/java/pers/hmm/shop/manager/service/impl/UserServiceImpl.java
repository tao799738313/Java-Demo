package pers.hmm.shop.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hmm.shop.manager.entity.UserInfo;
import pers.hmm.shop.manager.mapper.sys.UserMapper;
import pers.hmm.shop.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
