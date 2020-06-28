package pers.hmm.shop.item.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.hmm.shop.common.pojo.PageResult;
import pers.hmm.shop.item.pojo.ItemBrand;
import pers.hmm.shop.item.rpc.ManagerFeign;
import pers.hmm.shop.item.service.ItemBrandService;

import java.util.List;

/**
 * @Author 胡敏敏
 * @Date 2019/7/6
 * @DESC:
 */
@RestController
@RequestMapping("brand")
public class ItemBrandController {
    private Logger logger = LoggerFactory.getLogger(ItemBrandController.class);
    @Autowired
    private ItemBrandService itemBrandService;
    @Autowired
    private ManagerFeign managerFeign;

    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<ItemBrand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        PageResult<ItemBrand> result = itemBrandService.queryBrandByPage(page, rows, sortBy, desc, key);
//        Object allBrands = managerFeign.findAllBrands();
////        logger.info(allBrands.toString());
        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping("saveBrand")
    public ResponseEntity<Void> saveBrand(ItemBrand brand, @RequestParam("cids") List<Long> cids) {
        itemBrandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
