package pers.hmm.shop.manager.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pers.hmm.shop.manager.redis.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisUtils redisUtils;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        Claims claims = Jwts.claims();
        claims.put("role", authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret11").compact();
        //在这里开发有状态的token 生成tokenid 将tokenid=token保存到redis 将tokenid返回到前台
        String tokenId = UUID.randomUUID().toString();
        redisUtils.set("user:" + tokenId, token, 1000);
        //返回前台tokenid
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String str = "{\"tokenId\":\"" + tokenId + "\"}";
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
