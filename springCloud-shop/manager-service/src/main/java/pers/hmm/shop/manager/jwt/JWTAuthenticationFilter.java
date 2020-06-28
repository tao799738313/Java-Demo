package pers.hmm.shop.manager.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pers.hmm.shop.manager.redis.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private RedisUtils redisUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
        super(authenticationManager);
        this.redisUtils = redisUtils;
    }

    /**
     * 在拦截器中获取token并解析，拿到用户信息，放置到SecurityContextHolder，这样便完成了springsecurity和jwt的整合。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String tokenId = request.getHeader("Authorization");
        String token = (String) redisUtils.get("user:" + tokenId);
        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey("MyJwtSecret11").parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            String user = claims.getSubject();
            @SuppressWarnings("unchecked")
            List<String> roles = claims.get("role", List.class);
            List<SimpleGrantedAuthority> auth = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, auth);
            }
            return null;
        }
        return null;
    }
}
