package com.example.sharding.service;

import javax.annotation.Resource;

import com.example.sharding.entity.Order;
import org.springframework.stereotype.Service;

import com.example.sharding.mapper.OrderMapper;

import java.util.ArrayList;

@Service
public class DemoService {

	@Resource
	private OrderMapper orderRepository;

	public void demo() {
//		for (int i = 0; i < 10; i++) {
//			Order order = new Order();
//			order.setOrderId(i);
//			order.setUserId(56);
//			order.setStatus("Tinko");
//			orderRepository.insert(order);
//		}

		ArrayList list = (ArrayList) orderRepository.list();
        list.forEach(li->{
			System.out.println(li);
		});
		ArrayList list2 = (ArrayList) orderRepository.list();
		list2.forEach(li->{
			System.out.println(li);
		});
		System.out.println("Insert Success");
	}
}