package pers.hmm.shop.item.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import pers.hmm.shop.item.pojo.ItemBrand;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author 胡敏敏
 * @Date 2019/7/6
 * @DESC:
 */
public interface ItemBrandMapper extends Mapper<ItemBrand> {

    /**
     * 插入品牌商品类别关系表
     * @param cid
     * @param bid
     * @return
     */
    @Insert("INSERT INTO shop_data.tb_category_brand (category_id, brand_id) VALUES (#{cid}, #{bid});")
    int insertCategoryBrandRel(@Param("cid") Long cid, @Param("bid") Long bid);
}
