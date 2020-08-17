//package com.pdt.moduleuser8001.web.bean;
//
//import lombok.Data;
//import org.elasticsearch.common.geo.GeoPoint;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.elasticsearch.annotations.GeoPointField;
//
//import java.io.Serializable;
//
//@Data
//@Document(indexName = "address", type = "article")
//public class Address implements Serializable {
//    @Id
//    private String id;
//
//    @Field(analyzer = "ik_max_word", type = FieldType.Text) // , searchAnalyzer = "ik_max_word"
//    private String location;
//
//    @GeoPointField
//    private GeoPoint geoPoint;
//
//    private String geo;
//
//    public GeoPoint getGeoPoint(){
//        String[] split = geo.split(",");
//        return new GeoPoint(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
//    }
//}