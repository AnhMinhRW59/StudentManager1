package com.example.shoppeerw59.repository;

import com.example.shoppeerw59.modal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
}
