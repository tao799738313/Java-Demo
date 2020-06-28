package pers.hmm.shop.common.exception;

import pers.hmm.shop.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author admin
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopException extends RuntimeException {
    private ExceptionEnums exceptionEnums;
}
