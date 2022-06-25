package com.home.work.repository;

import com.home.work.domain.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, Long> {
    List<Orders> findByMemberId(String memberId);
}
