package pers.hmm.shop.manager.web.leave;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("leave")
public class AskController {

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("ask")
    public void start() {
        String processDefinitionKey = "myProcess_1";
        Map<String, Object> variables = new HashMap<>();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionKey);
        System.out.println(processInstance.getName());
    }
}
