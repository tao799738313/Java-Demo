package pers.hmm.shop.manager.service;

import pers.hmm.shop.manager.entity.UserInfo;

public interface UserService {
    UserInfo findByUsername(String username);
}
