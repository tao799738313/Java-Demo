/**
 *
 */
package pers.hmm.shop.manager.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import pers.hmm.shop.manager.security.core.properties.SecurityConstants;
import pers.hmm.shop.manager.security.core.validate.common.ValidateCodeProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author humm
 * 生成图片验证码和手机验证码的请求入口
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        //先通过findValidateCodeProcessor(type)获取imageValidateCodeProcessor或者smsValidateCodeProcessor
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

}
