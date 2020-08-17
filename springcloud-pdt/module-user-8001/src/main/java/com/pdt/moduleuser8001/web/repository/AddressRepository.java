package com.pdt.moduleuser8001.web.repository;

import com.pdt.moduleuser8001.web.bean.Address;
import com.pdt.moduleuser8001.web.bean.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AddressRepository extends ElasticsearchRepository<Address,Long> {
    // 留空
}