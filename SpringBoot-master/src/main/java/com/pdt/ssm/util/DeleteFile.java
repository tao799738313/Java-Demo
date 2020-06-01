package com.pdt.ssm.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.pdt.ssm.config.filePath;

/**
 * Created by taotao on 2019/8/27.
 */

/**
 * 上传文件：
 * 使用MultipartFile类型接收前台上传的文件数据
 * 接收数据时要指定接收方式-->RequestMethod.POST
 *
 * @author 郑清
 */
@Controller
public class DeleteFile {

    @RequestMapping("/deleteFile")
    @ResponseBody //不写会默认返回当前路径！！
    public Map deleteFile(@RequestParam("fileName") String fileName) throws Exception, IOException {
        Map map = new HashMap();
        File file = new File(filePath,fileName);
        if(file.exists()){
            file.delete();
            map.put("code",20000);
            map.put("msg","文件删除成功");
        }else{
            map.put("msg","文件不存在");
        }
        return map;
    }

}

