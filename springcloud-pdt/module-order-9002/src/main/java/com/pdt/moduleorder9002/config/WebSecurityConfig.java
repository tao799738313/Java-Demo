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

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义登录身份认证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域
        http.cors();
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.csrf().disable();
        // 认证失败处理类
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointImpl())
                .accessDeniedHandler(new CustomAccessDeniedHandler());

        http.authorizeRequests()
            // 只有这个接口需要进行认证
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated();

        // 退出登录处理器
        http.logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutSuccessHandlerImpl());

        // 访问控制时登录状态检查过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
         // 忽略后就不会经过过滤器,登陆接口一定不能忽略
         web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
         web.ignoring().antMatchers("/ignore/*");
         web.ignoring().antMatchers("/","/redis","/swagger**/**","/webjars/**","/v2/**","/csrf");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}