package pers.hmm.shop.common.pojo;

import pers.hmm.shop.common.enums.ExceptionEnums;
import lombok.Data;

/**
 * @author  胡敏敏
 * Date 2019/7/5
 * DESC:
 */
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
