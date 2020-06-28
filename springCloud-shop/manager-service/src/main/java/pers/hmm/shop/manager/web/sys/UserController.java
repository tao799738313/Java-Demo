package pers.hmm.shop.manager.web.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    private Object getCurrentUser() {
        logger.info("接收到请求！");
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
