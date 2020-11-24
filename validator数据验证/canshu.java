package com.louis.springboot.demo.validator;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Controller
@RequestMapping("/validator")
@ResponseBody
@Validated
public class canshu {

    @GetMapping("/getUser")
    @ResponseBody
    // 注意：如果想在参数中使用 @NotNull 这种注解校验，就必须在类上添加 @Validated；
    public String getUser(@NotNull(message = "userId不能为空") Integer userId){

        return "string";
    }


    @RequestMapping("/bean")
    @ResponseBody()
    public TestBean get(@Valid @RequestBody TestBean testBean, HttpServletRequest req) {
        return testBean;
    }
}
