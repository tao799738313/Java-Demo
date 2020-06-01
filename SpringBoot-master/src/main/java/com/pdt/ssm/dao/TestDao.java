package com.pdt.ssm.dao;


import com.pdt.ssm.bean.TestBean;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by taotao on 2019/8/25.
 */

//这个接口类是专门写mybatis注解的
public interface TestDao {
    @Select("select * from t_user")
    ArrayList<TestBean> testByScanner();
}

