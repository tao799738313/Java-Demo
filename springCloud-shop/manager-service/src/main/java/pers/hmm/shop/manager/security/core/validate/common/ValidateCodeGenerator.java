/**
 * 
 */
package pers.hmm.shop.manager.security.core.validate.common;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author humm
 * 生成验证码的接口
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
	
}
