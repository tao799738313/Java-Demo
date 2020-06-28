package pers.hmm.ws.service;

import pers.hmm.ws.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author: hmm
 * @Created: 2019/10/15
 * @Description:
 * @Modified by:
 */
@WebService(targetNamespace = "http://127.0.0.1:9090/ws", name = "userPortType")
public interface UserService {
    /**
     * 添加用户接口
     * @param user
     */
    @WebMethod
    User addUser(@WebParam(name = "user") User user);
}
