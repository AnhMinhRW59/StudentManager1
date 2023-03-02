package com.example.shoppeerw59.controller;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Order;
import com.example.shoppeerw59.modal.request.*;
import com.example.shoppeerw59.service.impl.AccountService;
import com.example.shoppeerw59.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin("*")
@Validated
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/get-all")
    public List<Order> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')") // "has AnyAthortiry" Cấp quyền cho nhiều đối tượng

    public Order getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid CreateOrderReq req){
        service.create(req);
    }

    @PutMapping("/update/{id}")
    public Order update(@RequestBody UpdateOrderReq req1, @PathVariable int id){
        return service.update(id, req1);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')") // "has AnyAthortiry" Cấp quyền cho nhiều đối tượng

    Page<Order> search(@RequestBody SearchOrderReq req){
        return service.search(req);
    }
}
