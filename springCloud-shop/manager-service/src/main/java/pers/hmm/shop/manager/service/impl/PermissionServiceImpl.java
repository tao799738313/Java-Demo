package pers.hmm.shop.manager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import pers.hmm.shop.manager.entity.Permission;
import pers.hmm.shop.manager.entity.Role;
import pers.hmm.shop.manager.mapper.sys.PermissonMapper;
import pers.hmm.shop.manager.service.PermissionService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {
    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissonMapper permissonMapper;

    private Map<String, Collection<ConfigAttribute>> permissionMap;

    /**
     * 项目启动时加载
     */
    @PostConstruct
    private void initMap() {
        List<Permission> list = permissonMapper.findAll();
        if (list != null && list.size() > 0) {
            permissionMap = new HashMap<>();
            for (Permission permission : list) {
                List<Role> roles = permission.getRoles();
                if (roles != null && roles.size() > 0) {
                    Collection<ConfigAttribute> collection = new ArrayList<>();
                    for (Role role : roles) {
                        collection.add(new SecurityConfig("ROLE_" + role.getRoleName()));
                    }
                    permissionMap.put(permission.getUrl(), collection);
                }
            }
            System.out.println(permissionMap.toString());
        } else {
            logger.debug("加载url权限信息为空！");
        }
    }

    /**
     * 调用获取url和roles
     *
     * @return
     */
    @Override
    public Map<String, Collection<ConfigAttribute>> getPermissionMap() {
        if (permissionMap == null || permissionMap.isEmpty()) {
            initMap();
        }
        return permissionMap;
    }
}
