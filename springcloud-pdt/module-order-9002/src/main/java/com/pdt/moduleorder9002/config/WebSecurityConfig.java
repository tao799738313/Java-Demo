package com.pdt.moduleorder9002.config;
import com.pdt.moduleorder9002.handle.AuthenticationEntryPointImpl;
import com.pdt.moduleorder9002.handle.CustomAccessDeniedHandler;
import com.pdt.moduleorder9002.handle.LogoutSuccessHandlerImpl;
import com.pdt.moduleorder9002.security.JwtAuthenticationFilter;
import com.pdt.moduleorder9002.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 服务于登录
    @Autowired
    private UserDetailsService userDetailsService;

    // 服务于登录
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义登录身份认证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域接口允许，这不是开启跨域的意思
        http.cors();

        // 禁用 csrf, 默认是开启的
        // 开启的情况下，PATCH，POST，PUT和DELETE请求会被拒绝，并返回403
        // 难道GET请求就不会被csrf吗，实际是框架的设计者默认GET是获取数据，不会对数据进行修改，所以会对数据进行修改的请求方式都被阻止了
        http.csrf().disable();

        // 认证失败处理类
        http.exceptionHandling()
                // 服务于登录过程
                .authenticationEntryPoint(new AuthenticationEntryPointImpl())
                // 服务于授权过滤器
                .accessDeniedHandler(new CustomAccessDeniedHandler());

        // 配置登录接口，有其他登录接口还可以往后加
        http.authorizeRequests()
                // 只有这个接口需要进行认证
                .antMatchers("/login").permitAll()
                // 固定这么写就行
                .anyRequest().authenticated();

        // 服务于退出
        http.logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutSuccessHandlerImpl());

        // 访问控制时登录状态检查过滤器
        // 所有的请求，除了被忽略的都会经过这个过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略后就不会经过过滤器，登陆接口一定不能忽略
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        web.ignoring().antMatchers("/ignore/*");
        web.ignoring().antMatchers("/user/**");
        web.ignoring().antMatchers("/","/redis","/swagger**/**","/webjars/**","/v2/**");
    }

    // 固定写法
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}