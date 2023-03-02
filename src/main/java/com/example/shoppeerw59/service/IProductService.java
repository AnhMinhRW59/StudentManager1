package com.example.shoppeerw59.service;

import com.example.shoppeerw59.modal.entity.Account;
import com.example.shoppeerw59.modal.entity.Product;
import com.example.shoppeerw59.modal.request.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    Page<Product> search(SearchProductReq req);

    Product getById(int id);

    void create(CreateProductReq req);

    Product update(int id, UpdateProductReq updateProductReq);

    void delete(int id);
}

