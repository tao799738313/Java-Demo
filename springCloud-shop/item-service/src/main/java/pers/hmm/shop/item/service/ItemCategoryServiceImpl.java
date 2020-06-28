package pers.hmm.shop.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.hmm.shop.common.enums.ExceptionEnums;
import pers.hmm.shop.common.exception.ShopException;
import pers.hmm.shop.item.mapper.ItemCategoryMapper;
import pers.hmm.shop.item.pojo.ItemCategory;

import java.util.List;

/**
 * @author 胡敏敏
 * @Date 2019/7/6
 * @DESC:
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Override
    public List<ItemCategory> queryItemCategoryById(Long id) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setParentId(id);
        List<ItemCategory> list = itemCategoryMapper.select(itemCategory);
        if (CollectionUtils.isEmpty(list)) {
            throw new ShopException(ExceptionEnums.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
