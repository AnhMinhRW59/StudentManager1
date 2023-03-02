package com.example.shoppeerw59.controller;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.request.*;
import com.example.shoppeerw59.service.impl.AccountService;
import com.example.shoppeerw59.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/get-all")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid CreateProductReq req){
        service.create(req);
    }

    @PutMapping("/update/{id}")
    public Product update(@RequestBody UpdateProductReq req1, @PathVariable int id){
        return service.update(id, req1);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchProductReq req){
        return service.search(req);
    }

}

