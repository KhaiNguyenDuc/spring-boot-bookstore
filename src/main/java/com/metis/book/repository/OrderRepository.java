package com.metis.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metis.book.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}