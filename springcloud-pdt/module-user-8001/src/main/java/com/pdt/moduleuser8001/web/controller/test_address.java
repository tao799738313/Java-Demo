//package com.pdt.moduleuser8001.web.controller;
//
//import com.pdt.moduleuser8001.web.bean.Address;
//import com.pdt.moduleuser8001.web.repository.AddressRepository;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.common.geo.GeoDistance;
//import org.elasticsearch.common.text.Text;
//import org.elasticsearch.common.unit.DistanceUnit;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.*;
//
//@Controller
//@ResponseBody
//@RequestMapping("/es_address")
//public class test_address {
//
//
//    @Autowired
//    AddressRepository addressRepository;
//
//    @RequestMapping("/save")
//    public void save() {
//        Address address = new Address();
//        address.setId(UUID.randomUUID().toString());
//        address.setLocation("昆山市前进西路1801号");
//        address.setGeo("31.386311,120.916435");
//        addressRepository.save(address);
//    }
//
//
//    public void saveAll() throws IOException {
//        File geo = ResourceUtils.getFile("classpath:geo.json");
//        String content = new String(Files.readAllBytes(geo.toPath()));
//        List<Address> list = JSONUtil.toList(JSONUtil.parseArray(content), Address.class);
//        addressRepository.saveAll(list);
//    }
//
//    @RequestMapping("/findAll")
//    public void findAll() {
//        Iterable<Address> all = addressRepository.findAll();
//        for (Address address : all) {
//            System.out.println(address.toString());
//        }
//    }
//
//    public void page() {
//        BoolQueryBuilder qb = QueryBuilders.boolQuery();
//        qb.must(QueryBuilders.termQuery("location", "周庄"));
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<Address> search = addressRepository.search(qb, pageable);
//        System.out.println("==" + search.getTotalElements());
//        System.out.println("==" + search.getTotalPages());
//        search.getContent().forEach(address -> System.out.println(JSONUtil.toJsonStr(address)));
//    }
//
//
//    public void ikSearch() {
//        String searchField = "location";
//        // 构造查询条件,使用标准分词器.
//        QueryBuilder matchQuery = QueryBuilders.boolQuery()
//                .must(QueryBuilders.multiMatchQuery("昆山", searchField).analyzer("ik_max_word")
//                        .operator(Operator.OR));
//
//        int pageNo = 1;
//        int pageSize = 10;
//
//        // 设置高亮,使用默认的highlighter高亮器
//        HighlightBuilder highlightBuilder = createHighlightBuilder(searchField);
//
//        // 设置查询字段
//        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch("address")
//                .setQuery(matchQuery)
//                .highlighter(highlightBuilder)
//                .setFrom((pageNo - 1) * pageSize)
//                // 设置一次返回的文档数量，最大值：10000
//                .setSize(pageNo * pageSize)
//                .get();
//
//        // 返回搜索结果
//        SearchHits hits = response.getHits();
//
//        List<Address> hitList = getHitList(hits);
//
//        System.out.println(hits.getTotalHits());
//        hitList.forEach(address -> {
//            System.out.println(JSONUtil.toJsonStr(address));
//        });
//    }
//
//    /**
//     * 构造高亮器
//     */
//    private HighlightBuilder createHighlightBuilder(String... fieldNames) {
//        // 设置高亮,使用默认的highlighter高亮器
//        HighlightBuilder highlightBuilder = new HighlightBuilder()
//                // .field("productName")
//                .preTags("")
//                .postTags("");
//
//        // 设置高亮字段
//        for (String fieldName : fieldNames) {
//            highlightBuilder.field(fieldName);
//        }
//
//        return highlightBuilder;
//    }
//
//
//    /**
//     * 处理高亮结果
//     */
//    private List<Address> getHitList(SearchHits hits) {
//        List<Map<String, Object>> list = new ArrayList<>();
//        Map<String, Object> map;
//        for (SearchHit searchHit : hits) {
//            // 处理源数据
//            map = new HashMap<>(searchHit.getSourceAsMap());
//            // 处理高亮数据
//            Map<String, Object> hitMap = new HashMap<>();
//            searchHit.getHighlightFields().forEach((k, v) -> {
//                StringBuilder high = new StringBuilder();
//                for (Text text : v.getFragments()) {
//                    high.append(text.string());
//                }
//                hitMap.put(v.getName(), high.toString());
//            });
//            map.putAll(hitMap);
//            list.add(map);
//        }
//        return JSON.parseArray(JSON.toJSONString(list), Address.class);
//    }
//
//
//    /**
//     * 按照位置远近搜索
//     */
//
//    public void sortByLocation() {
//        Pageable pageable = PageRequest.of(0, 20);
//        //搜索字段为 location
//        GeoDistanceQueryBuilder geoBuilder = new GeoDistanceQueryBuilder("geoPoint");
//        geoBuilder.point(31.186245, 121.037991);//指定从哪个位置搜索
//        geoBuilder.distance(100, DistanceUnit.KILOMETERS);//指定搜索多少km
//
//        //距离排序
//        GeoDistanceSortBuilder sortBuilder = new GeoDistanceSortBuilder("geoPoint", 31.186245, 121.037991);
//        sortBuilder.order(SortOrder.ASC);//升序
//        sortBuilder.unit(DistanceUnit.METERS);
//
//        //构造查询器
//        NativeSearchQueryBuilder qb = new NativeSearchQueryBuilder()
//                .withPageable(pageable)
//                .withFilter(geoBuilder)
//                .withSort(sortBuilder);
//
//        //可添加其他查询条件
//        //qb.must(QueryBuilders.matchQuery("address", address));
//        Page<Address> page = addressRepository.search(qb.build());
//        List<Address> list = page.getContent();
//        list.forEach(l -> {
//            double calculate = GeoDistance.PLANE.calculate(l.getGeoPoint().getLat(), l.getGeoPoint().getLon(), 31.186245, 121.037991, DistanceUnit.METERS);
//            System.out.println(JSONUtil.toJsonStr(l));
//            System.out.println("距离" + (int) calculate + "m");
//        });
//
//    }
//}
