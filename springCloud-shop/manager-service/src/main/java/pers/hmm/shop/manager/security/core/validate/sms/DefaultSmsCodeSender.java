/**
 * 
 */
package pers.hmm.shop.manager.security.core.validate.sms;

/**
 * @author humm
 * 发送短信
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	/*
	 * 给用户手机发送短信
	 */
	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机"+mobile+"发送短信验证码"+code);
	}

}
