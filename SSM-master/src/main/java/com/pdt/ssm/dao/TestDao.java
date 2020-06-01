package com.pdt.ssm.dao;


import com.pdt.ssm.bean.TestBean;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by taotao on 2019/8/25.
 */

//这个接口类是专门写mybatis注解的
@CacheNamespace(implementation = com.pdt.ssm.util.RedisCache.class)
public interface TestDao {
    @Select("select * from t_test")
    ArrayList<TestBean> testByScanner();

//    使用参数 https://www.cnblogs.com/siye1989/p/11621712.html
//    @Select("select *from User")
//    public List<User> retrieveAllUsers();
//
//    //注意这里只有一个参数，则#{}中的标识符可以任意取
//    @Select("select *from User where id=#{idss}")
//    public User retrieveUserById(int id);
//
//    @Select("select *from User where id=#{id} and userName like #{name}")
//    public User retrieveUserByIdAndName(@Param("id")int id,@Param("name")String names);
//
//    @Insert("INSERT INTO user(userName,userAge,userAddress) VALUES(#{userName},"
//            + "#{userAge},#{userAddress})")
//    public void addNewUser(User user);
//
//    @Delete("delete from user where id=#{id}")
//    public void deleteUser(int id);
//
//    @Update("update user set userName=#{userName},userAddress=#{userAddress}"
//            + " where id=#{id}")
//    public void updateUser(User user);
}

