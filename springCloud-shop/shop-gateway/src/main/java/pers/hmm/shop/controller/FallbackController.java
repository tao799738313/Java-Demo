/**
 * Copyright (c) 2019, NewStrength. All rights reserved.
 * 2019-01-22 09:20:17
 */
package pers.hmm.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hmm.shop.common.enums.ExceptionEnums;
import pers.hmm.shop.common.exception.ShopException;

/**
 * 断路器
 * @author Xd
 *
 */
@RestController
public class FallbackController {

	@RequestMapping(value = "/fallback",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ShopException fallback() {
		return new ShopException(ExceptionEnums.SERVICE_UNAVAILABLE);
	}
}
