package pers.hmm.shop.common.advice;

import pers.hmm.shop.common.exception.ShopException;
import pers.hmm.shop.common.enums.ExceptionEnums;
import pers.hmm.shop.common.pojo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Date 2019-07-05
 * 说明：全局异常处理类
 *      因为此类在common Module中，使用的服务启动类需要扫描的包名需要包含全局异常类所在包名，不然会失效
 * @author 胡敏敏
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ShopException.class)
    public ResponseEntity<ExceptionResult> handleException(ShopException e) {
        ExceptionEnums em = e.getExceptionEnums();
        return ResponseEntity.status(em.getCode()).body(new ExceptionResult(em));
    }
}
