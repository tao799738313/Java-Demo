package com.pdt.moduleuser8001.web.repository;

import com.pdt.moduleuser8001.web.bean.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    // 留空
}