package pers.hmm.shop.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pers.hmm.shop.manager.service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 返回访问路径要求的角色集合
 * 如果访问路径没有匹配值  就返回admin角色
 */
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        Map<String, Collection<ConfigAttribute>> map = permissionService.getPermissionMap();
        FilterInvocation filterInvocation = (FilterInvocation) object;
        System.out.println(filterInvocation.getFullRequestUrl());

        //return null 表示允许访问，不做拦截
        if (isMatcherAllowedRequest(filterInvocation)) return null;

        HttpServletRequest request = filterInvocation.getHttpRequest();
        String resUrl;
        //URL规则匹配.
        AntPathRequestMatcher matcher;
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            resUrl = it.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                System.out.println(map.get(resUrl));
                return map.get(resUrl);
            }
        }
        //SecurityConfig.createList("ROLE_USER");
        //方式一：没有匹配到,直接是白名单了.不登录也是可以访问的。
        //return null;

        //方式二：没有匹配到，需要指定相应的角色：
        return SecurityConfig.createList("ROLE_admin");
    }

    private boolean isMatcherAllowedRequest(FilterInvocation invocation) {
        return allowedRequest().stream().map(AntPathRequestMatcher::new)
                .filter(requestMatcher -> requestMatcher.matches(invocation.getHttpRequest()))
                .toArray().length > 0;
    }

    private List<String> allowedRequest() {
        return Arrays.asList("/login", "/css/**", "/fonts/**", "/js/**", "/scss/**", "/img/**");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
