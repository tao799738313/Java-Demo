package pers.hmm.shop.item.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: hmm
 * @Created: 2019/10/17
 * @Description:
 * @Modified by:
 */
@FeignClient("manager-service")
public interface ManagerFeign {

    @GetMapping("findAll")
    Object findAllBrands();
}
