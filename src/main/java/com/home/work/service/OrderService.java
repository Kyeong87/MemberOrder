package com.home.work.service;

import com.home.work.domain.Orders;
import com.home.work.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public List<Orders> getOrderDetail(String memberId){
        List<Orders> result = orderRepository.findByMemberId(memberId);
        if(result == null){
            return new ArrayList<>();
        }
        return result;
    }

    @Transactional
    public double setOrders(Orders orders, String memberId) {

        Orders order = new Orders();
        order.setOrderNumber(orders.getOrderNumber());
        order.setMemberId(memberId);
        order.setName(orders.getName());
        LocalDateTime now = LocalDateTime.now();
        order.setRegisterDate(now);
        orderRepository.save(order);

        return 200;
    }
}
