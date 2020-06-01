package com.pdt.ssm.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TestDaoImpl {
//   通过SqlSessionTemplate的方法才能操作数据库（这是mybatis的）
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public ArrayList testByXml() {
        return (ArrayList) sqlSessionTemplate.selectList("mapper.TestDao.testByXml");
//使用参数
//        List<Student> selectBetweenCreatedTimeAnno(@Param("bTime")Date pbTime, @Param("eTime")Date peTime);
//<select id="selectBetweenCreatedTimeAnno" resultMap="BaseResultMap">
//                select
//                <include refid="Base_Column_List" />
//                from student
//        where gmt_created &gt; #{bTime, jdbcType=TIMESTAMP} and gmt_created &lt; #{eTime, jdbcType=TIMESTAMP}
//  </select>
    }

//    通过JdbcTemplate的方法才能操作数据库
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList testByTemplate() {
        String sql = "select * from t_test";
        return (ArrayList) jdbcTemplate.queryForList(sql);

//        String sql = "select * from t_test where aa= ?";
//        return jdbcTemplate.query(sql,String.class,new Object[]{canshu}); 返回类型是第二个参数
//  除了query,queryForList,queryForObject,queryForMap 结果需要定义接受类型外
//  update(INSERT/UPDATE,DELETE),接过都是int
    }

}
