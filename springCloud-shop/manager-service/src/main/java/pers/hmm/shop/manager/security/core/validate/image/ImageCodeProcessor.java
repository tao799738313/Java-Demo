/**
 *
 */
package pers.hmm.shop.manager.security.core.validate.image;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import pers.hmm.shop.manager.redis.RedisUtils;
import pers.hmm.shop.manager.security.core.validate.common.AbstractValidateCodeProcessor;

import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 *
 * @author humm
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Autowired
    private RedisUtils redisUtils;

    private static ThreadLocal<Long> imageKey;

    /**
     * 图片验证码保存至redis
     * @param request
     * @param validateCode
     */
    @Override
    protected void save(ServletWebRequest request, ImageCode validateCode) {
        imageKey = ThreadLocal.withInitial(RandomUtils::nextLong);
        redisUtils.set(getRedisKey()+ ":" + imageKey.get().toString(), validateCode.getCode(), 30000);
    }

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        request.getResponse().setHeader("image_token", imageKey.get().toString());
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}
