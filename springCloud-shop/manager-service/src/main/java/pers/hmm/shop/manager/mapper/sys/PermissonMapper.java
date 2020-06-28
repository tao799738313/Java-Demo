package pers.hmm.shop.manager.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import pers.hmm.shop.manager.entity.Permission;

import java.util.List;

@Mapper
public interface PermissonMapper {
    List<Permission> findAll();
}
