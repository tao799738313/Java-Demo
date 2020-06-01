package com.pdt.ssm.service;


import com.pdt.ssm.dao.TestDao;
import com.pdt.ssm.dao.impl.TestDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


/**
 * Created by taotao on 2019/8/25.
 */
@Service
@Transactional
public class TestService {
//    mybatis_xml和jdbctemplate是来自DaoImpl的
    @Autowired
    private TestDaoImpl testDaoImpl;
//    mybatis_注解是来自userDao的
    @Autowired
    private TestDao testDao;

    public ArrayList testByXml() {
        return testDaoImpl.testByXml();
    }
    public ArrayList testByTemplate() {
        return testDaoImpl.testByTemplate();
    }
    public ArrayList testByScanner() {
        return testDao.testByScanner();
    }
}
