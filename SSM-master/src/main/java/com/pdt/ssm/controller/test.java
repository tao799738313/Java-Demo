package com.pdt.ssm.controller;



import com.alibaba.fastjson.JSON;
import com.pdt.ssm.bean.TestBean;
import com.pdt.ssm.service.TestService;
import com.pdt.ssm.util.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taotao on 2019/7/31.
 */

@Controller
public class test {
    @Autowired
    private TestService testService;

    @Autowired
    RedisDao redisDao;

    @RequestMapping("/get")
    @ResponseBody()
    public Map get(HttpServletRequest req,@Valid TestBean testBean) {
        //get方法,
        // 不管提交方式Form,也就是application/x-www-form-urlencoded;时
        // 还是json，也就是application/json;
        // req.getParameterMap()和bean都能接受到参数
        // get是不能传数组或者对象的,可以数组和对象转成json字符串,但是需要去一个个转
        Map map = new HashMap();
        for(String key : req.getParameterMap().keySet()){
            map.put(key,req.getParameterMap().get(key)[0]);
        }
        System.out.println(testBean.toString());
        //如果用bean去接受,结果不能bean,需要把bean转成字符串
        String str = JSON.toJSONString(testBean);
        redisDao.setKey("测试","测试1");
        return map;
    }

    @RequestMapping(value="/post",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody String str) {
        //post方法,
        // 提交方式Form,也就是application/x-www-form-urlencoded;时，req.getParameterMap()和bean都能接受到,但是不能传数组和对象,可以数组和对象转成json字符串,但是需要去一个个转
        // 提交是json，也就是application/json;时req.getParameterMap()拿不到值，bean也拿不到,但是可以用@RequestBody去接，然后转成bean或者obj或者list
        // 推荐用application/json的请求头提交,然后用@RequestBody接收
        Object obj = JSON.parse(str);
        TestBean testBean = JSON.parseObject(str,TestBean.class);
        System.out.println(testBean.toString());
        return obj;
    }



    // 这是用mybatis的xml查询的，实在SqlSessionFactory里配置mapperLocations
    @RequestMapping("/testByXml")
    @ResponseBody
    @Cacheable(value="test",key="1")
    public ArrayList testByXml() {
        return testService.testByXml();
    }


    //这是用mybatis注解查询的，是在MapperScannerConfigurer配置basePackage
    @RequestMapping("/testByScanner")
    @ResponseBody
    public ArrayList testByScanner() {
        return testService.testByScanner();
    }


    //    这是用jdbctemplate查询的，实在JdbcTemplate配置dataSource
    @RequestMapping("/testByTemplate")
    @ResponseBody
    public ArrayList getAllByTemplate() {
        return testService.testByTemplate();
    }
}
