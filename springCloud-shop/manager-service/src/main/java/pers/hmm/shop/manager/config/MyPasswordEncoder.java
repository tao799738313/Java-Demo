package pers.hmm.shop.manager.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import pers.hmm.shop.common.utils.MD5Utils;

/**
 * 自定义密码加密方式
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Utils.encode((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Utils.encode((String) rawPassword));
    }
}
