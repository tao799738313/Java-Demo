/**
 * 
 */
package pers.hmm.shop.manager.security.core.validate.sms;

/**
 * @author humm
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
