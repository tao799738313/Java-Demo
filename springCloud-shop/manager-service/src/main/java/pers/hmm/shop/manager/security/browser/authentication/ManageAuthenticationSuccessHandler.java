/**
 *
 */
package pers.hmm.shop.manager.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pers.hmm.shop.manager.security.core.properties.LoginResponseType;
import pers.hmm.shop.manager.security.core.properties.SecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author humm
 *
 */
@Component
public class ManageAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.
     * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }

}
