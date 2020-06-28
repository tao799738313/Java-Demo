/**
 *
 */
package pers.hmm.shop.manager.security.core.validate;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCodeException;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCodeProcessor;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCodeType;

/**
 * @author humm
 *
 */
@Component
public class ValidateCodeProcessorHolder {

    /**
     * spring容器启动后 将ValidateCodeProcessor接口的实现类
     * name和实例以map的形式保存到map中
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * 根据请求路径/image  /sms和 ValidateCodeProcessor拼接为
     * imageValidateCodeProcessor或者smsValidateCodeProcessor
     * 然后从初始化的map中获取实例
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
