package pers.hmm.ws;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import pers.hmm.ws.entity.User;

/**
 * @Author: hmm
 * @Created: 2019/10/16
 * @Description:
 * @Modified by:
 */
public class Client {

    public static void main(String args[]) throws Exception {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:9090/ws/user?wsdl");
        Object[] objects = client.invoke("addUser", new User());
        //输出调用结果
        System.out.println("*****" + objects[0].toString());
    }
}
