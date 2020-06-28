/**
 * 
 */
package pers.hmm.shop.manager.security.core.validate.common;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器接口
 * 
 * @author humm
 *
 */
public interface ValidateCodeProcessor {

	/**
	 * 验证码放入redis
	 */
	String REDIS_KEY_PREFIX = "KEY_FOR_CODE_";

	/**
	 * 创建校验码
	 * 
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}
