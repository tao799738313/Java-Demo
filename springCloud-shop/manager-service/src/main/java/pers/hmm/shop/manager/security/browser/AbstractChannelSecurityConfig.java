/**
 * 
 */
package pers.hmm.shop.manager.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pers.hmm.shop.manager.security.core.properties.SecurityConstants;

/**
 * @author humm
 * 多一层封装 ：表单登录配置
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler manageAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler manageAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(manageAuthenticationSuccessHandler)
			.failureHandler(manageAuthenticationFailureHandler);
	}
}
