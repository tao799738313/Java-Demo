/**
 *
 */
package pers.hmm.shop.manager.security.core.validate.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import pers.hmm.shop.manager.redis.RedisUtils;
import pers.hmm.shop.manager.security.core.properties.SecurityConstants;
import pers.hmm.shop.manager.security.core.validate.common.AbstractValidateCodeProcessor;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCode;

/**
 * 短信验证码处理器
 * 获取前台传入的手机号码
 * @author humm
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void save(ServletWebRequest request, ValidateCode validateCode) {
        String mobile = request.getParameter(mobileParameter);
        redisUtils.set(getRedisKey() + ":" + mobile, validateCode.getCode(), 30000);
    }

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
