/**
 *
 */
package pers.hmm.shop.manager.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.hmm.shop.manager.security.core.properties.SecurityProperties;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCodeGenerator;
import pers.hmm.shop.manager.security.core.validate.image.ImageCodeGenerator;
import pers.hmm.shop.manager.security.core.validate.sms.DefaultSmsCodeSender;
import pers.hmm.shop.manager.security.core.validate.sms.SmsCodeSender;

/**
 * @author humm
 * 初始化注入图片和短信生成类
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
