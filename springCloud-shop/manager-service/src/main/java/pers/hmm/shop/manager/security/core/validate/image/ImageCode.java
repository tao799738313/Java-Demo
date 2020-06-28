/**
 *
 */
package pers.hmm.shop.manager.security.core.validate.image;

import pers.hmm.shop.manager.security.core.validate.common.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 * @author humm
 *
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
