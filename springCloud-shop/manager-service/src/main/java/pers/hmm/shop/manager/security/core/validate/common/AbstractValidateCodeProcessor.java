/**
 *
 */
package pers.hmm.shop.manager.security.core.validate.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import pers.hmm.shop.manager.redis.RedisUtils;

import java.util.Map;

/**
 * @author humm
 * 验证码生成、创建、发送的抽象类
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * redis工具类
     */
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /**
     * 生成、保存、发送验证码
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    protected abstract void save(ServletWebRequest request, C validateCode);

    /**
     * 构建验证码放入redis时的key
     *
     * @return
     */
    protected String getRedisKey() {
        return REDIS_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     *
     * @return
     */
    protected ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType processorType = getValidateCodeType();
        String redisKey = getRedisKey();


        String codeInRedis = null;

        String codeInRequest;
        String keyInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    processorType.getParamNameOnValidate());
            keyInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    processorType.getKeyNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }
        //redis中保存的code
        codeInRedis = (String) redisUtils.get(redisKey + ":" + keyInRequest);

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }

        if (codeInRedis == null) {
            throw new ValidateCodeException(processorType + "验证码不存在或已过期");
        }

        if (!StringUtils.equals(codeInRedis, codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }

        redisUtils.del(redisKey + ":" + keyInRequest);
    }

}
