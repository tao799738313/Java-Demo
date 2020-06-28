package pers.hmm.shop.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pers.hmm.shop.common.enums.ExceptionEnums;
import pers.hmm.shop.common.exception.ShopException;
import pers.hmm.shop.common.pojo.PageResult;
import pers.hmm.shop.item.mapper.ItemBrandMapper;
import pers.hmm.shop.item.pojo.ItemBrand;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author 胡敏敏
 * @Date 2019/7/6
 * @DESC:
 */
@Service
public class ItemBrandServiceImpl implements ItemBrandService {

    @Autowired
    private ItemBrandMapper itemBrandMapper;

    @Transactional
    @Override
    public PageResult<ItemBrand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(ItemBrand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%"+key+"%").orEqualTo("letter", key.toUpperCase());
        }
        //排序
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " DESC" : "ASC"));
        }
        //查询
        List<ItemBrand> list = itemBrandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new ShopException(ExceptionEnums.Brand_NOT_FOUND);
        }
        PageInfo<ItemBrand> info = new PageInfo<>(list);

        return new PageResult<>(info.getTotal(), info.getPages(), list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBrand(ItemBrand brand, List<Long> cids) {
        //新增品牌
        int count = itemBrandMapper.insert(brand);
        if (count != 1) {
            throw new ShopException(ExceptionEnums.BRAND_ADD_ERROR);
        }
        //新增中间表
        for (Long cid : cids) {
            int i = itemBrandMapper.insertCategoryBrandRel(cid, brand.getId());
            if (i != 1) {
                throw new ShopException(ExceptionEnums.BRAND_ADD_ERROR);
            }
        }
    }
}
