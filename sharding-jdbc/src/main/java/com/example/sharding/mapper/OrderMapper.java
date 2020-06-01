package com.example.sharding.mapper;

import com.example.sharding.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
	Long insert(Order order);

	@Select("select user_id as userId,order_id as orderId from t_order")
	List<Order> list();
}