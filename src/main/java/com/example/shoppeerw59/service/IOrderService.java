package com.example.shoppeerw59.service;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Order;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.request.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();

    Order getById(int id);

    void create(CreateOrderReq req);

    Order update(int id, UpdateOrderReq updateOrderReq);

    void delete(int id);

    Page<Order> search(SearchOrderReq req);
}
