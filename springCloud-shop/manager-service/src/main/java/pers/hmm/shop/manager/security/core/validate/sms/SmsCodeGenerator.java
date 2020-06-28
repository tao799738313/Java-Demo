/**
 *
 */
package pers.hmm.shop.manager.security.core.validate.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import pers.hmm.shop.manager.security.core.properties.SecurityProperties;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCode;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCodeGenerator;

/**
 * @author humm
 * 手机验证码生成器
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /*
     * 生成短信验证码
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
