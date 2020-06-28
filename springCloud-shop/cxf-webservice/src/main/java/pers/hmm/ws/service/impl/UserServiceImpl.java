package pers.hmm.ws.service.impl;

import org.springframework.stereotype.Service;
import pers.hmm.ws.entity.User;
import pers.hmm.ws.service.UserService;

import javax.jws.WebService;

/**
 * @Author: hmm
 * @Created: 2019/10/15
 * @Description:
 * @Modified by:
 */
@Service
@WebService(targetNamespace = "http://127.0.0.1:9090/ws",
                name = "userPortType", serviceName="userService",
                portName = "userPortName")//指定发布webservcie的接口类，此类也需要接入@WebService注解
public class UserServiceImpl implements UserService {
    @Override
    public User addUser(User user) {
        return user;
    }
}
