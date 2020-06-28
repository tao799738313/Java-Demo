package pers.hmm.shop.item.service;

import pers.hmm.shop.common.pojo.PageResult;
import pers.hmm.shop.item.pojo.ItemBrand;

import java.util.List;

/**
 * @Author 胡敏敏
 * @Date 2019/7/6
 * @DESC: 商品品牌接口
 */
public interface ItemBrandService {

    PageResult<ItemBrand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    void saveBrand(ItemBrand brand, List<Long> cids);
}
