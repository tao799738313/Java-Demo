/**
 *
 */
package pers.hmm.shop.manager.security.core.validate.common;

import pers.hmm.shop.manager.security.core.properties.SecurityConstants;

/**
 * @author humm
 *
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }

        @Override
        public String getKeyNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_KEY_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }

        @Override
        public String getKeyNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_KEY_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取验证码
     * @return
     */
    public abstract String getParamNameOnValidate();

    /**
     * 校验时从请求中获取验证码存储的key
     * @return
     */
    public abstract String getKeyNameOnValidate();

}
