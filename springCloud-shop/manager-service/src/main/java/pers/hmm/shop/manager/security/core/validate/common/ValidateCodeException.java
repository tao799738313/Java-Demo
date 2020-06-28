/**
 * 
 */
package pers.hmm.shop.manager.security.core.validate.common;

import org.springframework.security.core.AuthenticationException;

/**
 * @author humm
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
