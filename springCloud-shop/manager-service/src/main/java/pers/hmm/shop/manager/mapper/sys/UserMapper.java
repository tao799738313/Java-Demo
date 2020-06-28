package pers.hmm.shop.manager.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import pers.hmm.shop.manager.entity.UserInfo;

@Mapper
public interface UserMapper {
    UserInfo findByUsername(String username);
}
